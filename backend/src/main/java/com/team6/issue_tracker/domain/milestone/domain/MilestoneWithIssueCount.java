package com.team6.issue_tracker.domain.milestone.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.Instant;

@Getter
@Builder
public class MilestoneWithIssueCount {
    private Long milestoneIdx;
    private String title;
    private Instant endedAt;
    private String contents;
    private Boolean isOpen;
    private Long totalIssueNum;
    private Long closedIssueNum;
}
