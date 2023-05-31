package com.team6.issue_tracker.domain.page.dto;

import com.team6.issue_tracker.domain.issue.domain.Issue;
import com.team6.issue_tracker.domain.label.dto.LabelSummary;
import com.team6.issue_tracker.domain.member.dto.MemberDto;
import com.team6.issue_tracker.domain.milestone.domain.Milestone;
import com.team6.issue_tracker.domain.model.Status;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;


@Data
@Builder
public class IssueDto {

    private Long index;
    private String title;
    private MemberDto writer;
    private MemberDto assignee;
    private Status status;
    private Instant createdAt;
    private Instant editedAt;
    //TODO milestone dto로 바꾸기
    private Milestone milestone;
    private List<LabelSummary> labelList;

    public IssueDto(Long index, String title, MemberDto writer, MemberDto assignee, Status status, Instant createdAt, Instant edditedAt, Milestone milestone, List<LabelSummary> labelList) {
        this.index = index;
        this.title = title;
        this.writer = writer;
        this.assignee = assignee;
        this.status = status;
        this.createdAt = createdAt;
        this.editedAt = edditedAt;
        this.milestone = milestone;
        this.labelList = labelList;
    }

    public static IssueDto toDto(Issue issue, MemberDto writer, MemberDto assignee,
                                 List<LabelSummary> labels, Milestone milestone) {
        return IssueDto.builder()
                .index(issue.getIssueIdx())
                .title(issue.getTitle())
                .writer(writer)
                .assignee(assignee)
                .status(Status.of(issue.getIsOpen()))
                .createdAt(issue.getCreatedAt())
                .labelList(labels)
                .milestone(milestone)
                .build();
    }

}
