package com.kazlabs.twitterclone.api.repository;

import com.kazlabs.twitterclone.api.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
