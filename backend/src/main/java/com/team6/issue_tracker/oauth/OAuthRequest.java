package com.team6.issue_tracker.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuthRequest {

    // clientId , clientSecret , code , redirect_uri
    private String code;

    @Value("${spring.security.oauth2.client.registration.github.clientId}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.github.clientSecret}")
    private String clientSecret;
//    private String redirect_uri;

    public OAuthRequest setCode(String code) {
        this.code = code;
        return this;
    }
}
