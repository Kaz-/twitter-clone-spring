package com.kazlabs.twitterclone.api.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String username;
    private String name;
    private String bio;
    private String email;
    private String password;
}
