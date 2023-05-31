package com.team6.issue_tracker.global.auth.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GithubOAuthProperties {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
}
