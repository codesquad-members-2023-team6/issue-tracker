package com.team6.issue_tracker.domain.member.service;

import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.global.auth.domain.GithubUser;
import com.team6.issue_tracker.global.auth.dto.GithubAccessToken;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class MemberProvider {

    public Member toMemberEntity(GithubUser githubUser, GithubAccessToken githubAccessToken) {
        return Member.builder()
                .memberIdx(null)
                .id(githubUser.getLogin())
                .name(String.valueOf(githubUser.getId()))
                .profileImageUrl(githubUser.getAvatarUrl())
                .accessToken(githubAccessToken.getAccessToken())
                .createdAt(Instant.now())
                .build();

    }
}
