package com.example.demo.Security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.Security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(COURCE_READ, STUDENT_READ, COURCE_WRITE, STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(COURCE_READ, STUDENT_READ)),
    STUDENT(Sets.newHashSet()); //zero permission

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions){
        this.permissions=permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                        .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));//ROLE_ADMIN OR ROLE_STUDENT OR ...
        //.add in set will add element as hash set (not in order)
        //here this refers to Role which can be (ADMIN, ADMINTRAINEE and STUDENT)
        return permissions;
    }
}
