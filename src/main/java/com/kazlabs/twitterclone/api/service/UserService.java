package com.kazlabs.twitterclone.api.service;

import com.kazlabs.twitterclone.api.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    User getUserByUsername(String username);
}
