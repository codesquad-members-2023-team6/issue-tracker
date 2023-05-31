package com.team6.issue_tracker.domain.issue.dto;

import com.team6.issue_tracker.domain.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateIssueStatusRequest {
    Status status;
}
