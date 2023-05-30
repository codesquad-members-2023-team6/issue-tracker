package com.team6.issue_tracker.domain.issue.dto;

import com.team6.issue_tracker.domain.issue.domain.Issue;
import com.team6.issue_tracker.domain.issue.domain.Labeling;
import com.team6.issue_tracker.domain.label.dto.LabelDto;
import com.team6.issue_tracker.domain.member.dto.MemberDto;
import com.team6.issue_tracker.domain.milestone.domain.Milestone;
import lombok.Data;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CreateIssueRequest {
    private String title;
    private String contents;
    private MemberDto writer;
    private MemberDto assignee;
    private List<LabelDto> labels;
    private Milestone milestone;

    public Issue toIssue() {
        Map<Long, Labeling> labelingMap = new HashMap<>();
        labels.stream().map(e -> new Labeling(e.getLabelIdx()))
                .forEach(l -> labelingMap.put(l.getLabelIdx(), l));

        return Issue.builder()
                .issueIdx(null)
                .title(title)
                .contents(contents)
                .writer(AggregateReference.to(writer.getMemberIdx()))
                .assignee(AggregateReference.to(assignee.getMemberIdx()))
                .labelOnIssue(labelingMap)
                .milestone(AggregateReference.to(milestone.getMilestoneIdx()))
                .createdAt(Instant.now())
                .isOpen(true)
                .isDeleted(false)
                .build();
    }
}
