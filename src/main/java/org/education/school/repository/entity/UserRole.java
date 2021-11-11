package org.education.school.repository.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN, USER, TEACHER;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
