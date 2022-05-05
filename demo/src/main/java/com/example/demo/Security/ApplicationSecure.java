package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.Security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecure extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder; //that will give me object of BCryptPasswordEncoder

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(); //Basic Auth in Postman
    }

    @Override
    @Bean //create object of UserDetailsService
    protected UserDetailsService userDetailsService() {
        UserDetails Waleed = User.builder()
                .username("waleed")
                .password(passwordEncoder.encode("123456789"))
                .roles(ADMIN.name())
                .build();
        UserDetails Anas = User.builder()
                .username("anas")
                .password(passwordEncoder.encode("54321"))
                .roles(STUDENT.name())
                .build();
        return new InMemoryUserDetailsManager(Waleed, Anas);
    }
}
