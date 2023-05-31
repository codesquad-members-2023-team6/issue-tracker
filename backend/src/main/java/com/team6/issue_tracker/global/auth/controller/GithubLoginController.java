package com.team6.issue_tracker.global.auth.controller;

import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.domain.member.service.MemberProvider;
import com.team6.issue_tracker.domain.member.service.MemberService;
import com.team6.issue_tracker.global.auth.config.GithubOAuthProperties;
import com.team6.issue_tracker.global.auth.domain.GithubUser;
import com.team6.issue_tracker.global.auth.dto.GithubAccessToken;
import com.team6.issue_tracker.global.auth.dto.GithubAccessTokenRequest;
import com.team6.issue_tracker.global.auth.service.GithubOAuthService;
import com.team6.issue_tracker.global.auth.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GithubLoginController {
    // login 요청 url
    // https://github.com/login/oauth/authorize?client_id=ded3580dcda54e46b774&redirect_uri=http://localhost:8080/oauth/result&scope=user
    // Redirect
    // http://localhost:8080/oauth/result?code=e2b73a0322cc4b86a685

    private final GithubOAuthProperties githubOAuthProperties;
    private final MemberService memberService;
    private final MemberProvider memberProvider;
    private final GithubOAuthService oAuthServices;
    private final JwtService jwtService;

    public GithubLoginController(GithubOAuthProperties githubOAuthProperties, MemberService memberService, MemberProvider memberProvider, GithubOAuthService oAuthServices, JwtService jwtService) {
        this.githubOAuthProperties = githubOAuthProperties;
        this.memberService = memberService;
        this.memberProvider = memberProvider;
        this.oAuthServices = oAuthServices;
        this.jwtService = jwtService;
    }

    @GetMapping("/oauth/result")
    public ResponseEntity<?> loginViaGithub(String code) {
        GithubAccessTokenRequest githubAccessTokenRequest = new GithubAccessTokenRequest(githubOAuthProperties, code);
        GithubAccessToken githubAccessToken = oAuthServices.requestAccessToken(githubAccessTokenRequest);
        GithubUser githubUser = oAuthServices.requestUserInfo(githubAccessToken);

        // 맴버 등록 및 업데이트
        Member createdMember = memberProvider.toMemberEntity(githubUser, githubAccessToken);
        memberService.join(createdMember);

        //토큰 jwt 처리
        String jwtToken = jwtService.createToken(githubUser);
        // header, body에 토큰 넣고 반환
        return jwtService.createResponse(jwtToken);
    }
}
