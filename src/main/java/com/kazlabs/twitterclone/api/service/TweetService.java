package com.kazlabs.twitterclone.api.service;

import com.kazlabs.twitterclone.api.entity.Tweet;

import java.util.List;
import java.util.Optional;

public interface TweetService {

    List<Tweet> findAll();

    Optional<Tweet> findById(Long id);

    Tweet save(Tweet tweet);

    void deleteById(Long id);

    boolean existsById(Long id);
}
