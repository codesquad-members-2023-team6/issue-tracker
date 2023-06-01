package com.team6.issue_tracker.domain.page.dto;

import com.team6.issue_tracker.domain.label.dto.LabelSummary;
import com.team6.issue_tracker.domain.member.dto.MemberDetail;
import com.team6.issue_tracker.domain.milestone.domain.Milestone;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class IssuePageResponse {

    private Long openIssueCount;
    private Long closedIssueCount;
    private Integer page;
    private Integer openIssueMaxPage;
    private Integer closeIssueMaxPage;
    private List<IssueDto> issueList;
    private List<LabelSummary> labelList;
    private List<Milestone> milestoneList;
    private List<MemberDetail> userList;

}
