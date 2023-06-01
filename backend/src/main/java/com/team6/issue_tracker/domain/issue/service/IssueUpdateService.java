package com.team6.issue_tracker.domain.issue.service;

import com.team6.issue_tracker.domain.issue.domain.Issue;
import com.team6.issue_tracker.domain.issue.repository.IssueRepository;
import com.team6.issue_tracker.domain.model.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueUpdateService {

    private final IssueRepository issueRepository;

    public Issue updateIssue(Issue updatedIssue) {
        return issueRepository.save(updatedIssue);
    }

    public boolean updateIssueListStatus(List<Long> issueIdx, Status status) {
        log.info("issueidx = {}, status = {}", issueIdx, status);
        return issueRepository.updateIssuesIsOpen(status == Status.OPEN, issueIdx);
    }

    public boolean updateIssueStatus(Long index, Status status) {
        return issueRepository.updateIssueIsOpen(status == Status.OPEN, index);
    }

    public boolean updateIssueTitle(Long issueIdx, String title) {
        return issueRepository.updateIssueTitle(title, issueIdx);
    }

    public boolean updateIssueAssignee(long issueIdx, Long assigneeIdx) {
        return issueRepository.updateIssueAssignee(assigneeIdx, issueIdx);
    }
}
