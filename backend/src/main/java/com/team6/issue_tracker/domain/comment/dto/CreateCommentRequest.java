package com.team6.issue_tracker.domain.comment.dto;

import com.team6.issue_tracker.domain.member.dto.MemberDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {
    private String contents;
    private MemberDetail writer;

    public Long getWriterIdx() {
        return writer.getMemberIdx();
    }
}
