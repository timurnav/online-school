package org.education.school.service.dto;

import org.education.school.repository.entity.UserRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class UserCredentials implements UserDetails {

    public final int id;
    public final String email;
    private final String password;
    private final Set<UserRole> roles;
    private final boolean banned;

    public UserCredentials(int id, String email, String password, Set<UserRole> roles, boolean banned) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.banned = banned;
    }

    @Override
    public Set<UserRole> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !banned;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !banned;
    }
}
