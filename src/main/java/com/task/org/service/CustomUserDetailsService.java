package com.task.org.service;

import com.task.org.domain.UserPrinciple;
import com.task.org.domain.Users;
import com.task.org.repository.CustomUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomUserRepo customUserRepo;

    public CustomUserDetailsService(CustomUserRepo customUserRepo) {
        this.customUserRepo = customUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = customUserRepo.findByUsername(username).orElseThrow(()->{
            throw new UsernameNotFoundException("user not found");
        });
        return new UserPrinciple(user);
    }
}
