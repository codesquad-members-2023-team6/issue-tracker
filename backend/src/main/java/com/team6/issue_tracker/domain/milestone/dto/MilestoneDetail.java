package com.team6.issue_tracker.domain.milestone.dto;

import com.team6.issue_tracker.domain.milestone.domain.MilestoneWithIssueCount;
import com.team6.issue_tracker.domain.model.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class MilestoneDetail {
    private Long index;
    private String title;
    private String contents;
    private Status status;
    private Instant endDate;
    private Long totalIssueNum;
    private Long closedIssueNum;

    public static MilestoneDetail fromMilestone(MilestoneWithIssueCount milestoneDetail) {
        return MilestoneDetail.builder()
                .index(milestoneDetail.getMilestoneIdx())
                .title(milestoneDetail.getTitle())
                .contents(milestoneDetail.getContents())
                .status(Status.of(milestoneDetail.getIsOpen()))
                .endDate(milestoneDetail.getEndedAt())
                .totalIssueNum(milestoneDetail.getTotalIssueNum())
                .closedIssueNum(milestoneDetail.getClosedIssueNum())
                .build();
    }
}
