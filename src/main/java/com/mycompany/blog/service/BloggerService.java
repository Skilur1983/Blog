package com.mycompany.blog.service;

import com.mycompany.blog.model.Authority;
import com.mycompany.blog.model.Blogger;
import com.mycompany.blog.repositories.AuthoritiesRepository;
import com.mycompany.blog.repositories.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class BloggerService {

    @Autowired
    private BloggerRepository bloggerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthoritiesRepository authorityRepository;

    public Blogger save(Blogger account) {

        if (account.getId() == null) {
            if (account.getAuthorities().isEmpty()) {
                Set<Authority> authorities = new HashSet<>();
                authorityRepository.findById("ROLE_USER").ifPresent(authorities::add);
                account.setAuthorities(authorities);
            }
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return bloggerRepository.save(account);
    }

    public Optional<Blogger> findOneByEmail(String email) {
        return bloggerRepository.findUserByEmail(email);
    }

    public Blogger findOneById(Long id) {
        return bloggerRepository.findUserById(id);
    }

}
