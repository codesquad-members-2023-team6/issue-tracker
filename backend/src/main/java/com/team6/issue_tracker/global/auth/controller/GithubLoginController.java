package com.team6.issue_tracker.global.auth.controller;

import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.domain.member.dto.MemberDetail;
import com.team6.issue_tracker.domain.member.service.MemberProvider;
import com.team6.issue_tracker.domain.member.service.MemberService;
import com.team6.issue_tracker.global.auth.config.GithubOAuthPropertiesProd;
import com.team6.issue_tracker.global.auth.config.GithubOAuthPropertiesDev;
import com.team6.issue_tracker.global.auth.domain.GithubUser;
import com.team6.issue_tracker.global.auth.dto.GithubAccessToken;
import com.team6.issue_tracker.global.auth.dto.GithubAccessTokenRequest;
import com.team6.issue_tracker.global.auth.service.GithubOAuthService;
import com.team6.issue_tracker.global.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GithubLoginController {

    private final String ENV_PROD = "prod";
    private final String ENV_DEV = "dev";

    private final GithubOAuthPropertiesProd githubOAuthPropertiesProd;
    private final GithubOAuthPropertiesDev githubOAuthPropertiesDev;
    private final MemberService memberService;
    private final MemberProvider memberProvider;
    private final GithubOAuthService oAuthServices;
    private final JwtService jwtService;

    @GetMapping("/oauth/result")
    public ResponseEntity<?> loginViaGithub(String code, String env) throws LoginException {
        log.info("[OAuth called]");
        log.info("code = {} env = {}", code, env);
        GithubAccessTokenRequest githubAccessTokenRequest = null;
        if (env.equals(ENV_DEV)) {
            githubAccessTokenRequest = new GithubAccessTokenRequest(githubOAuthPropertiesDev, code);
        } else if (env.equals(ENV_PROD)) {
            githubAccessTokenRequest = new GithubAccessTokenRequest(githubOAuthPropertiesProd, code);
        }

        log.debug("githubAccessTokenRequest = {}", githubAccessTokenRequest);
        GithubAccessToken githubAccessToken = oAuthServices.requestAccessToken(githubAccessTokenRequest);
        GithubUser githubUser = oAuthServices.requestUserInfo(githubAccessToken);

        // 맴버 등록 및 업데이트
        Member createdMember = memberProvider.toMemberEntity(githubUser, githubAccessToken);
        Member member = memberService.join(createdMember);

        //토큰 jwt 처리
        String jwtToken = jwtService.createToken(MemberDetail.from(member));
        // header, body에 토큰 넣고 반환
        return jwtService.createResponse(jwtToken);
    }
}
