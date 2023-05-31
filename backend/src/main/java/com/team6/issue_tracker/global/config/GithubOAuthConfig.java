package com.team6.issue_tracker.global.config;

import com.team6.issue_tracker.global.auth.config.GithubOAuthPropertiesDev;
import com.team6.issue_tracker.global.auth.config.GithubOAuthPropertiesProd;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubOAuthConfig {

    @Value("${spring.security.oauth2.client.registration.github.clientId}")
    String clientId;

    @Value("${spring.security.oauth2.client.registration.github.clientSecret}")
    String clientSecret;

    @Value("${spring.security.oauth2.client.registration.github.redirectUri}")
    String redirectUri;

    @Value("${spring.security.oauth2.client.registration.github-dev.clientId}")
    String clientIdDev;

    @Value("${spring.security.oauth2.client.registration.github-dev.clientSecret}")
    String clientSecretDev;

    @Value("${spring.security.oauth2.client.registration.github-dev.redirectUri}")
    String redirectUriDev;

    @Bean
    public GithubOAuthPropertiesProd githubOAuthProdProperties() {
        return new GithubOAuthPropertiesProd(clientId, clientSecret, redirectUri);
    }

    @Bean
    public GithubOAuthPropertiesDev githubOAuthDevProperties() {
        return new GithubOAuthPropertiesDev(clientIdDev, clientSecretDev, redirectUriDev);
    }
}
