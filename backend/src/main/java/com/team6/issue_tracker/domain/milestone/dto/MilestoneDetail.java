package com.team6.issue_tracker.domain.milestone.dto;

import com.team6.issue_tracker.domain.milestone.domain.MilestoneWithIssueCount;
import com.team6.issue_tracker.domain.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneDetail {
    private Long milestoneIdx;
    private String title;
    private String contents;
    private Status status;
    private Instant endDate;
    private Long totalIssueNum;
    private Long closedIssueNum;

    public static MilestoneDetail fromMilestone(MilestoneWithIssueCount milestoneDetail) {
        return MilestoneDetail.builder()
                .milestoneIdx(milestoneDetail.getMilestoneIdx())
                .title(milestoneDetail.getTitle())
                .contents(milestoneDetail.getContents())
                .status(Status.of(milestoneDetail.getIsOpen()))
                .endDate(milestoneDetail.getEndedAt())
                .totalIssueNum(milestoneDetail.getTotalIssueNum())
                .closedIssueNum(milestoneDetail.getClosedIssueNum())
                .build();
    }
}
