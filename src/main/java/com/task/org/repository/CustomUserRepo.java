package com.task.org.repository;

import com.task.org.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserRepo extends JpaRepository<Users,Integer> {
    public Users findByUsername(String name);
}
