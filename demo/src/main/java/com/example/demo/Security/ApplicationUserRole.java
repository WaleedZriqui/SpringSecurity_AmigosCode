package com.example.demo.Security;

import com.google.common.collect.Sets;

import java.util.Set;

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
}
