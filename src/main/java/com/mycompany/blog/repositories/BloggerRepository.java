package com.mycompany.blog.repositories;

import com.mycompany.blog.model.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Long> {
    Optional<Blogger> findUserByEmail(String email);
    Blogger findUserById(Long id);
}
