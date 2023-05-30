package com.team6.issue_tracker.domain.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@Table("member")
public class Member {

    @Id
    private Long memberIdx;

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    private String profileImageUrl;

    @PersistenceCreator
    public Member(Long memberIdx, String id, String name, String password, String profileImageUrl) {
        this.memberIdx = memberIdx;
        this.id = id;
        this.name = name;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
    }

    private Member(String id, String name, String password, String profileImageUrl) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
    }

    public static Member newMember(String id, String name, String password, String profileImageUrl) {
        return new Member(id, name, password, profileImageUrl);
    }
}
