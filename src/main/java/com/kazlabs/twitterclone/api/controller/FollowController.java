package com.kazlabs.twitterclone.api.controller;

import com.kazlabs.twitterclone.api.entity.User;
import com.kazlabs.twitterclone.api.repository.UserRepository;
import com.kazlabs.twitterclone.api.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/follows")
public class FollowController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowService followService;


    @PostMapping("/{id}")
    public ResponseEntity<?> followUser(@PathVariable Long id, Authentication authentication) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        try{
            followService.followUser(authentication, id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> unfollowUser(@PathVariable Long id, Authentication authentication) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        try{
            followService.unfollowUser(authentication, id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
