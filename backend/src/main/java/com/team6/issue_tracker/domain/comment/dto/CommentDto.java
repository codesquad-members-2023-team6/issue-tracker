package com.team6.issue_tracker.domain.comment.dto;

import com.team6.issue_tracker.domain.comment.domain.Comment;
import com.team6.issue_tracker.domain.comment.domain.CommentContents;
import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.domain.member.dto.MemberDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.Instant;

@Data @Builder
@AllArgsConstructor
public class CommentDto {

    private Long commentIdx;
    private String contents;
    private MemberDetail writer;
    private Instant createdAt;
    private Instant editedAt;

    public static CommentDto fromComment(Comment comment, Member writer) {
        return CommentDto.builder()
                .commentIdx(comment.getCommentIdx())
                .contents(comment.getContents().getContents())
                .createdAt(comment.getCreatedAt())
                .editedAt(comment.getEditedAt())
                .writer(MemberDetail.from(writer))
                .build();
    }

    public Comment toUpdatedComment(long issueIdx) {
        return Comment.builder()
                .commentIdx(commentIdx)
                .contents(new CommentContents(contents))
                .createdAt(createdAt)
                .editedAt(Instant.now())
                .createdBy(AggregateReference.to(writer.getMemberIdx()))
                .issueIdx(AggregateReference.to(issueIdx))
                .build();
    }
}
