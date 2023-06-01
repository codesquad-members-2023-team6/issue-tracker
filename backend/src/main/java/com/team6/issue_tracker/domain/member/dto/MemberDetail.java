package com.team6.issue_tracker.domain.member.dto;

import com.team6.issue_tracker.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetail {
    Long memberIdx;
    String id;
    String name;
    String profileImageUrl;

    public static MemberDetail from(Member member) {
        if (member!=null) {
            return new MemberDetail(member.getMemberIdx(), member.getId(), member.getName(), member.getProfileImageUrl());
        }
        return null;
    }
}
