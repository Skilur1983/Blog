package com.mycompany.blog.config;

import com.mycompany.blog.model.Authority;
import com.mycompany.blog.repositories.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbPreSets implements CommandLineRunner {

    @Autowired
    private AuthoritiesRepository authorityRepository;


    @Override
    public void run(String... args) throws Exception {

        Authority user = new Authority();
        user.setName("ROLE_USER");
        authorityRepository.save(user);

        Authority admin = new Authority();
        admin.setName("ROLE_ADMIN");
        authorityRepository.save(admin);
    }
}
