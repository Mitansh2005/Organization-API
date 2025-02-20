package com.task.org.repository;

import com.task.org.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomUserRepo extends JpaRepository<Users,Integer> {
    public Optional<Users> findByUsername(String name);
}
