package com.team6.issue_tracker.domain.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateIssueMilestoneRequest {
    private Long milestoneIdx;
}
