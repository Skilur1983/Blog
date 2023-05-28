package com.mycompany.blog.config;

import com.mycompany.blog.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.mycompany.blog.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String email;
    private String password;
    Collection<? extends GrantedAuthority> authorities;
    public CustomUserDetails(User byUserName){
        this.email = byUserName.getEmail();
        this.password = byUserName.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();
        for(Role role : byUserName.getRoles()) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        this.authorities = auths;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
