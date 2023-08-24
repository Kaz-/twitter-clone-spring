package com.kazlabs.twitterclone.api.controller;

import com.kazlabs.twitterclone.api.dao.request.TweetRequest;
import com.kazlabs.twitterclone.api.entity.Tweet;
import com.kazlabs.twitterclone.api.entity.User;
import com.kazlabs.twitterclone.api.service.TweetService;
import com.kazlabs.twitterclone.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tweets")
public class TweetController {

    private final TweetService tweetService;
    private final UserService userService;
    @Autowired
    public TweetController(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }



    @GetMapping("/")
    public ResponseEntity<List<Tweet>> getAllTweets() {
        return ResponseEntity.ok(tweetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable Long id) {
        return tweetService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Tweet> createTweet(@RequestBody TweetRequest tweetRequest, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.getUserByUsername(userDetails.getUsername());
        var tweet  = Tweet.builder()
                .content(tweetRequest.getContent())
                .build();
        tweet.setUser(user);

        Tweet createdTweet = tweetService.save(tweet);
        return ResponseEntity.ok(createdTweet);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id) {
        if (!tweetService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        tweetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
