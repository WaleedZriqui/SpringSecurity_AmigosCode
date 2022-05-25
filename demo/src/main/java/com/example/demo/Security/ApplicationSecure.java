package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static com.example.demo.Security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecure extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder; //that will give me object of BCryptPasswordEncoder

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()//Type of Authorization in Postman is Form Auth
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(25))
                    .key("Very Dangerous")
                .and()
                .logout()
                     .logoutUrl("/logout") //by default
                     .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) //to specify it get not post
                     .clearAuthentication(true) //remove the Authentication from the current SecurityContext if it is true (default)
                     .invalidateHttpSession(true) //invalidate the HttpSession if it is true and the session is not null.
                     .deleteCookies("JSESSIONID","remember-me") //it will remove both cookies (JSESSIONID, remember-me) when logout
                     .logoutSuccessUrl("/login"); //when logout success it goes to this url
    }

    @Override
    @Bean //create object of UserDetailsService
    protected UserDetailsService userDetailsService() { //to be cancelled create new class that return Authentication provider
        UserDetails Waleed = User.builder()
                .username("waleed")
                .password(passwordEncoder.encode("123456789"))
                .authorities(ADMIN.getGrantedAuthority())
                .build();
        UserDetails Anas = User.builder()
                .username("anas")
                .password(passwordEncoder.encode("54321"))
                .authorities(STUDENT.getGrantedAuthority())
                .build();
        UserDetails Amr = User.builder()
                .username("amr")
                .password(passwordEncoder.encode("2006"))
                .authorities(ADMINTRAINEE.getGrantedAuthority())
                .build();
        return new InMemoryUserDetailsManager(Waleed, Anas, Amr);
    }
}