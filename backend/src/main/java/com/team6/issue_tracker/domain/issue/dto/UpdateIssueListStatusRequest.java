package com.team6.issue_tracker.domain.issue.dto;

import com.team6.issue_tracker.domain.model.Status;
import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateIssueListStatusRequest {
    List<Long> issueIdx;
    Status status;
}
