package com.team6.issue_tracker.domain.issue.domain;

import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.domain.milestone.domain.Milestone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@Table("issue")
public class Issue {

    @Id
    private Long issueIdx;
    @NotNull
    private String title;
    private String contents;
    private Boolean isOpen;
    private Boolean isDeleted;

    @CreatedBy
    private AggregateReference<Member, @NotNull Long> writer;
    private AggregateReference<Member, @NotNull Long> assignee;

    private AggregateReference<Milestone, @NotNull Long> milestone;

    @Valid
    @MappedCollection(idColumn = "issue_idx", keyColumn = "labeling_idx")
    @Builder.Default
    private Map<Long, Labeling> labelOnIssue = new HashMap<>();

    @NotNull
    @CreatedDate
    @PastOrPresent
    private Instant createdAt;

    @LastModifiedDate
    @PastOrPresent
    private Instant editedAt;

}
