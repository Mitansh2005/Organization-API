package com.task.org.domain;

import com.task.org.enums.Authority;
import com.task.org.enums.Role;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrinciple implements UserDetails {
    private final Users users;

    public UserPrinciple(Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ users.getRole().name()));
        if (users.getRole() == Role.EMPLOYEE){
            authorities.add(new SimpleGrantedAuthority(Authority.VIEW_PROJECTS.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.VIEW_EMPLOYEES.name()));
        } else if (users.getRole()== Role.MANAGER) {
            authorities.add(new SimpleGrantedAuthority(Authority.VIEW_PROJECTS.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.EDIT_PROJECTS.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.VIEW_EMPLOYEES.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.EDIT_EMPLOYEES.name()));
        } else if (users.getRole()==Role.BOSS) {
            authorities.add(new SimpleGrantedAuthority(Authority.VIEW_PROJECTS.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.EDIT_PROJECTS.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.VIEW_DEPARTMENTS.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.EDIT_DEPARTMENTS.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.VIEW_EMPLOYEES.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.EDIT_EMPLOYEES.name()));
        } else if (users.getRole() == Role.ADMIN) {
            authorities.add(new SimpleGrantedAuthority(Authority.VIEW_PROJECTS.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.EDIT_PROJECTS.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.VIEW_DEPARTMENTS.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.EDIT_DEPARTMENTS.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.VIEW_ORGANIZATIONS.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.EDIT_ORGANIZATIONS.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.VIEW_EMPLOYEES.name()));
            authorities.add(new SimpleGrantedAuthority(Authority.EDIT_EMPLOYEES.name()));
        }else {
            throw new AuthorizationDeniedException("You do not have the authority to access this resource");
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
