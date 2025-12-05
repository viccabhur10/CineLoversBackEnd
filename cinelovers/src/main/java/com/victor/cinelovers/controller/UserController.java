package com.victor.cinelovers.controller;

import com.victor.cinelovers.entity.User;
import com.victor.cinelovers.repository.UserRepository; // ¡Tendrás que crear este repositorio igual que el de Movie!
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REGISTRO DE USUARIO
    @PostMapping("/register")
    public User registerUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }
}