package com.team6.issue_tracker.domain.member.dto;

import com.team6.issue_tracker.domain.member.domain.Member;
import lombok.Data;

@Data
public class MemberDto {
    Long memberIdx;
    String id;
    String name;
    String profileImageUrl;

    public MemberDto(Long memberIdx, String name, String profileImageUrl) {
        this.memberIdx = memberIdx;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public static MemberDto from(Member member) {
        return new MemberDto(member.getMemberIdx(), member.getId(), member.getProfileImageUrl());
    }
}
