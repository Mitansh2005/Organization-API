package com.task.org.service;

import com.task.org.domain.Users;
import com.task.org.repository.CustomUserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final CustomUserRepo customUserRepo;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(
            CustomUserRepo customUserRepo
            , AuthenticationManager authenticationManager
            , JWTService jwtService) {
        this.customUserRepo = customUserRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public List<Users> getAllUsers() {
        return customUserRepo.findAll().stream().toList();
    }

    public Users getUser(Integer id) {
        return customUserRepo.findById(id).orElseThrow(() -> {
            throw new IllegalStateException("No user with this id: " + id);
        });
    }

    public void addUser(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        customUserRepo.save(users);
    }

    public String verify(Users users) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
        return authentication.isAuthenticated() ? jwtService.generateToken(users.getUsername()) : "Failure";
    }
}
