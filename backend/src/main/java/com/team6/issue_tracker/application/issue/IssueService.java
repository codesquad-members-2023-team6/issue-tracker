package com.team6.issue_tracker.application.issue;

import org.springframework.stereotype.Service;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    }
}
