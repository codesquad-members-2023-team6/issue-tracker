package com.team6.issue_tracker.domain.member.dto;

import com.team6.issue_tracker.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {
    Long memberIdx;
    String id;
    String name;
    String profileImageUrl;

    public static MemberDto from(Member member) {
        return new MemberDto(member.getMemberIdx(), member.getId(), member.getName(), member.getProfileImageUrl());
    }
}
