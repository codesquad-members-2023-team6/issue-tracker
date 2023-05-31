package com.team6.issue_tracker.domain.comment.domain;

import com.team6.issue_tracker.domain.issue.domain.Issue;
import com.team6.issue_tracker.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.*;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;

@Getter
@Builder
@ToString
@Table("comment")
public class Comment {

    @Id
    private Long commentIdx;

    @Embedded.Nullable
    private CommentContents contents;

    private AggregateReference<Issue, @NotNull Long> issueIdx;

    @CreatedBy
    private AggregateReference<Member, @NotNull Long> createdBy;

    @NotNull
    @CreatedDate
    @PastOrPresent
    private Instant createdAt;

    @LastModifiedDate
    @PastOrPresent
    private Instant editedAt;

    private Boolean isDeleted;

    private Comment(String contents, AggregateReference<Issue, @NotNull Long> issueIdx, AggregateReference<Member, @NotNull Long> createdBy, Instant createdAt) {
        this.contents = new CommentContents(contents);
        this.issueIdx = issueIdx;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    @PersistenceCreator
    public Comment(Long commentIdx, CommentContents contents, AggregateReference<Issue, @NotNull Long> issueIdx, AggregateReference<Member, @NotNull Long> createdBy, Instant createdAt, Instant editedAt, Boolean isDeleted) {
        this.commentIdx = commentIdx;
        this.contents = contents;
        this.issueIdx = issueIdx;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.editedAt = editedAt;
        this.isDeleted = isDeleted;
    }

    public static Comment newComment(String contents, Long issueIdx, Long memberIdx) {
        return new Comment(contents, AggregateReference.to(issueIdx), AggregateReference.to(memberIdx), Instant.now());
    }
}
