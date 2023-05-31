package com.team6.issue_tracker.domain.page.service;

import com.team6.issue_tracker.domain.issue.service.IssueService;
import com.team6.issue_tracker.domain.issue.domain.Issue;
import com.team6.issue_tracker.domain.model.Status;
import com.team6.issue_tracker.domain.page.dto.IssueDto;
import com.team6.issue_tracker.domain.page.dto.IssueFilter;
import com.team6.issue_tracker.domain.label.service.LabelService;
import com.team6.issue_tracker.domain.label.dto.LabelSummary;
import com.team6.issue_tracker.domain.member.service.MemberService;
import com.team6.issue_tracker.domain.member.dto.MemberDto;
import com.team6.issue_tracker.domain.milestone.domain.Milestone;
import com.team6.issue_tracker.domain.milestone.service.MilestoneService;
import com.team6.issue_tracker.domain.page.dto.IssuePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PageService {

    private static final Integer PAGE_SIZE = 20;

    private final IssueService issueService;
    private final MemberService memberService;
    private final LabelService labelService;
    private final MilestoneService milestoneService;

    public IssuePageResponse getAPage(int page, IssueFilter filter) {

        Map<Long, MemberDto> members = memberService.getAllMembers();
        Map<Long, Milestone> milestones = milestoneService.getAllMilestones();
        Map<Long, LabelSummary> labels = labelService.getAllLabelSummaries();

        List<Issue> issueList = issueService.findByfilterWithPage(page*PAGE_SIZE, PAGE_SIZE, filter);
        long openIssueNum = issueService.getIssueNum(Status.OPEN);
        long closedIssueNum = issueService.getIssueNum(Status.CLOSE);

        List<IssueDto> issueDtos = new ArrayList<>();

        for (Issue issue : issueList) {
            MemberDto writer = getWriter(members, issue);
            MemberDto assignee = getAssignee(members, issue);
            Milestone milestone = getMilestone(milestones, issue);
            List<LabelSummary> labelList = getLabelList(labels, issue);

            IssueDto issueDto = IssueDto.toDto(issue, writer, assignee, labelList, milestone);
            issueDtos.add(issueDto);
        }

        return IssuePageResponse.builder()
                .issueList(issueDtos)
                .openIssueCount(openIssueNum)
                .closedIssueCount(closedIssueNum)
                .page(page)
                .openIssueMaxPage(getIssueMaxPage(openIssueNum))
                .closeIssueMaxPage(getIssueMaxPage(closedIssueNum))
                .userList(new ArrayList<>(members.values()))
                .labelList(new ArrayList<>(labels.values()))
                .milestoneList(new ArrayList<>(milestones.values()))
                .build();
    }

    private List<LabelSummary> getLabelList(Map<Long, LabelSummary> labels, Issue issue) {
        return issue.getLabelOnIssue().values()
                        .stream()
                        .map(e -> labels.get(e.getLabelIdx()))
                        .collect(Collectors.toList());
    }

    private MemberDto getWriter(Map<Long, MemberDto> members, Issue issue) {
        return members.get(issue.getWriter().getId());
    }

    private Milestone getMilestone(Map<Long, Milestone> milestones, Issue issue) {
        Milestone milestone = null;
        if (issue.getMilestone() != null) {
            milestone = milestones.get(issue.getMilestone().getId());
        }
        return milestone;
    }

    private MemberDto getAssignee(Map<Long, MemberDto> members, Issue issue) {
        MemberDto assignee = null;
        if (issue.getAssignee() != null) {
            assignee = members.get(issue.getAssignee().getId());
        }
        return assignee;
    }

    public Integer getIssueMaxPage(long allIssue) {
        if (allIssue/PAGE_SIZE == 0) {
            return Math.toIntExact(allIssue / PAGE_SIZE);
        }
        return Math.toIntExact(allIssue / PAGE_SIZE + 1);
    }

}
