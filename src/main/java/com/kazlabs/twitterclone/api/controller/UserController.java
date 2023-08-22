package com.kazlabs.twitterclone.api.controller;

import com.kazlabs.twitterclone.api.entity.User;
import com.kazlabs.twitterclone.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping("/")
//    public User createUser(@RequestBody User user) {
//        return userRepository.save(user);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userRequest) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword()); // Note: Ensure to hash before saving.
            user.setEmail(userRequest.getEmail());
            user.setName(userRequest.getName());
            user.setBio(userRequest.getBio());
            return ResponseEntity.ok(userRepository.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
