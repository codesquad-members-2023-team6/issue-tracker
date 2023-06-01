package com.team6.issue_tracker.domain.comment.dto;

import com.team6.issue_tracker.domain.member.dto.MemberDetail;
import lombok.Data;

@Data
public class CreateCommentRequest {
    private String contents;
    private MemberDetail writer;

    public Long getWriterIdx() {
        return writer.getMemberIdx();
    }
}
