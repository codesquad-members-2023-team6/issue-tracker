package com.team6.issue_tracker.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OAuthController {
    // login 요청 url
    // https://github.com/login/oauth/authorize?client_id=ded3580dcda54e46b774&redirect_uri=http://localhost:8080/oauth/result&scope=user
    // Redirect
    // http://localhost:8080/oauth/result?code=e2b73a0322cc4b86a685

//    @GetMapping("/oauth/result")
//    public String oauthTestStep2(String code) {
//        return code;
//    }

}
