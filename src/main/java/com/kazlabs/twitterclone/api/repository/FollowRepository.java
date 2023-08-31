package com.kazlabs.twitterclone.api.repository;

import com.kazlabs.twitterclone.api.entity.Follow;
import com.kazlabs.twitterclone.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowedAndFollower(User followedUser, User loggedInUser);
}
