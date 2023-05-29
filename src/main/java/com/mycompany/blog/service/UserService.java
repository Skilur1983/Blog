package com.mycompany.blog.service;

import com.mycompany.blog.model.User;
import com.mycompany.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void save(User user){
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUser(String email) {
        return userRepository.findUserByName(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
