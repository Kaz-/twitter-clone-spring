package com.kazlabs.twitterclone.api.service.impl;

import com.kazlabs.twitterclone.api.entity.Tweet;
import com.kazlabs.twitterclone.api.repository.TweetRepository;
import com.kazlabs.twitterclone.api.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    @Override
    public Optional<Tweet> findById(Long id) {
        return tweetRepository.findById(id);
    }

    @Override
    public Tweet save(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    @Override
    public void deleteById(Long id) {
        tweetRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return tweetRepository.existsById(id);
    }
}
