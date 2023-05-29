package com.mycompany.blog;

import com.mycompany.blog.config.CustomUserDetails;
import com.mycompany.blog.model.Role;
import com.mycompany.blog.model.User;
import com.mycompany.blog.repositories.UserRepository;
import com.mycompany.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;

@SpringBootApplication
public class BlogMain {

    @Autowired
    private PasswordEncoder passwordEncoder;
    public static void main(String[] args){
        SpringApplication.run(BlogMain.class, args);
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService userService) throws Exception {
        if (repository.count()==0)
            userService.save(new User("admin", "adminPassword", Arrays.asList(new Role("USER"), new Role("ACTUATOR") , new Role("ADMIN"))));
        builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
    }

    private UserDetailsService userDetailsService(final UserRepository repository) {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return new CustomUserDetails(repository.findUserByName(username));
            }
        };
    }
}
