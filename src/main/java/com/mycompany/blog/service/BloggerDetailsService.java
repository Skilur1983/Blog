package com.mycompany.blog.service;

import com.mycompany.blog.model.Blogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class BloggerDetailsService implements UserDetailsService {

    @Autowired
    private BloggerService bloggerService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Blogger> optionalAccount = bloggerService.findOneByEmail(email);
        if (!optionalAccount.isPresent()) {
            throw new UsernameNotFoundException("Account not found");
        }
        Blogger blogger = optionalAccount.get();
        List<GrantedAuthority> grantedAuthorities = blogger
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(blogger.getEmail(), blogger.getPassword(), grantedAuthorities);
    }
}
