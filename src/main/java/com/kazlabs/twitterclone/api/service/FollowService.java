package com.kazlabs.twitterclone.api.service;

import org.springframework.security.core.Authentication;

public interface FollowService {
    void followUser(Authentication authentication, Long user_id) throws Exception;
    void unfollowUser(Authentication authentication, Long user_id) throws Exception;
}
