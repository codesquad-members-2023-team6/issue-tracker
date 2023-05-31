package com.team6.issue_tracker.domain.issue.dto;

import com.team6.issue_tracker.domain.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UpdateIssueListStatusRequest {
    List<Long> issueIdx;
    Status status;
}
