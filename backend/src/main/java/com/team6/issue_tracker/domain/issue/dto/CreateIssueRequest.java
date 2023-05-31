package com.team6.issue_tracker.domain.issue.dto;

import com.team6.issue_tracker.domain.issue.domain.Issue;
import com.team6.issue_tracker.domain.issue.domain.Labeling;
import com.team6.issue_tracker.domain.label.dto.LabelSummary;
import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.domain.member.dto.MemberDto;
import com.team6.issue_tracker.domain.milestone.domain.Milestone;
import lombok.Data;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CreateIssueRequest {
    private String title;
    private String contents;
    private MemberDto writer;
    private MemberDto assignee;
    private List<LabelSummary> labels;
    private Milestone milestone;

    public Issue toIssue() {

        return Issue.builder()
                .issueIdx(null)
                .title(title)
                .contents(contents)
                .writer(AggregateReference.to(writer.getMemberIdx()))
                .assignee(getAssigneeRef())
                .labelOnIssue(getLabelingMap(labels))
                .milestone(getMilestoneRef())
                .createdAt(Instant.now())
                .isOpen(true)
                .isDeleted(false)
                .build();
    }

    private List<Labeling> getLabelingMap (List<LabelSummary> labels) {
        List<Labeling> labelings= new ArrayList<>();
        labels.stream().map(e -> new Labeling(e.getLabelIdx()))
                .forEach(labelings::add);
        return labelings;
    }

    private AggregateReference<Member, Long> getAssigneeRef() {
        if (assignee != null) {
            return AggregateReference.to(assignee.getMemberIdx());
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
