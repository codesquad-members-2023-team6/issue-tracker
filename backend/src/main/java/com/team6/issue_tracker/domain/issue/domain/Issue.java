package com.team6.issue_tracker.domain.issue.domain;

import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.domain.milestone.domain.Milestone;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("issue")
public class Issue {

    @Id
    private Long issueIdx;
    private String title;
    private String contents;
    private Boolean isOpen;
    private Boolean isDeleted;

    @CreatedBy
    private AggregateReference<Member, Long> writer;
    private AggregateReference<Member, Long> assignee;
    private AggregateReference<Milestone, Long> milestone;

    @MappedCollection(idColumn = "issue_idx", keyColumn = "labeling_idx")
    @Builder.Default
    private List<Labeling> labelOnIssue = new ArrayList<>();

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant editedAt;

}
