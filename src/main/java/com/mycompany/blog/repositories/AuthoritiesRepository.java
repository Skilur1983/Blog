package com.mycompany.blog.repositories;

import com.mycompany.blog.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authority, String> {
}
