package com.team6.issue_tracker.auth;

import com.team6.issue_tracker.auth.domain.GithubUser;
import com.team6.issue_tracker.auth.dto.GithubAccessToken;
import com.team6.issue_tracker.auth.dto.GithubAccessTokenRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import static com.team6.issue_tracker.auth.config.AuthConst.*;

@Slf4j
@Service
public class GithubOAuthService {

    public GithubAccessToken requestAccessToken(GithubAccessTokenRequest request) {

        WebClient webClient = WebClient.builder()
                .baseUrl(GITHUB_URL)
                .build();

        GithubAccessToken gitHubAccessToken = webClient.post()
                .uri(TOKEN_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(
                        request.getParams()
                )
                .retrieve()
                .bodyToMono(GithubAccessToken.class)
                .blockOptional().orElseThrow();
        log.debug("gitHubAccessToken = {}", gitHubAccessToken);

        return gitHubAccessToken;
    }

    public GithubUser requestUserInfo(GithubAccessToken githubAccessToken) {
        WebClient webClient = WebClient.builder()
                .baseUrl(GITHUB_API)
                .build();

        return webClient.get()
                .uri(USER_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", githubAccessToken.authorizationHeaderValue())
                .header(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .retrieve()
                .bodyToMono(GithubUser.class)
                .block();
    }

}
