package com.example.tametable.entity;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permission.ROLE_ADMIN)),
    TEACHER(Set.of(Permission.ROLE_TEACHER)),
    STUDENT(Set.of(Permission.ROLE_STUDENT));

    private final Set<Permission> permission;

    Role(Set<Permission> permission) {
        this.permission = permission;
    }

    public Set<Permission> getPermission() {
        return permission;
    }


}
