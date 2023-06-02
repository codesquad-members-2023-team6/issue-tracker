package com.team6.issue_tracker.domain.issue.dto;

import com.team6.issue_tracker.domain.issue.domain.Issue;
import com.team6.issue_tracker.domain.issue.domain.Labeling;
import com.team6.issue_tracker.domain.label.dto.LabelSummary;
import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.domain.milestone.domain.Milestone;
import com.team6.issue_tracker.domain.milestone.dto.MilestoneDetail;
import lombok.Data;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateIssueRequest {
    private String title;
    private String contents;
    private Long writer;
    private Long assignee;
    private List<LabelSummary> labels = new ArrayList<>();
    private MilestoneDetail milestone;

    public Issue toIssue() {
        return Issue.builder()
                .title(title)
                .contents(contents)
                .writer(AggregateReference.to(writer))
                .assignee(getAssigneeRef())
                .labelOnIssue(getLabelingList(labels))
                .milestone(getMilestoneRef())
                .createdAt(Instant.now())
                .isOpen(true)
                .isDeleted(false)
                .build();
    }

    private List<Labeling> getLabelingList(List<LabelSummary> labels) {
        List<Labeling> labelings= new ArrayList<>();
        labels.stream().map(e -> new Labeling(AggregateReference.to(e.getLabelIdx())))
                    .forEach(labelings::add);
        return labelings;
    }

    private AggregateReference<Member, Long> getAssigneeRef() {
        if (assignee != null) {
            return AggregateReference.to(assignee);
        }
        return null;
    }

    private AggregateReference<Milestone, Long> getMilestoneRef() {
        if (milestone != null) {
            return AggregateReference.to(milestone.getMilestoneIdx());
        }
        return null;
    }
}
