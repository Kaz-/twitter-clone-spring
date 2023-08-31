package com.kazlabs.twitterclone.api.service.impl;

import com.kazlabs.twitterclone.api.entity.Follow;
import com.kazlabs.twitterclone.api.entity.User;
import com.kazlabs.twitterclone.api.repository.FollowRepository;
import com.kazlabs.twitterclone.api.repository.UserRepository;
import com.kazlabs.twitterclone.api.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    public void followUser(Authentication authentication, Long user_id) throws Exception
    {
        User LoggedInUser = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User FollowedUser = userRepository.findById(user_id).orElse(null);
        if(FollowedUser == null)
        {
            throw new Exception("User not found!");
        }
        Follow f = new Follow();
        f.setFollower(LoggedInUser);
        f.setFollowed(FollowedUser);
        followRepository.save(f);
    }

    public void unfollowUser(Authentication authentication, Long user_id) throws Exception
    {
        User LoggedInUser = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User FollowedUser = userRepository.findById(user_id).orElse(null);
        if(FollowedUser == null)
        {
            throw new Exception("User not found!");
        }
        Follow f = followRepository.findByFollowedAndFollower(FollowedUser,LoggedInUser).orElse(null);
        if(f == null)
        {
            throw new Exception("User not following " + FollowedUser.getUsername());
        }
        followRepository.delete(f);

    }

}
