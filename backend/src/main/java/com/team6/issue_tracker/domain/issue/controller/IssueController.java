package com.team6.issue_tracker.domain.issue.controller;

import com.team6.issue_tracker.domain.issue.domain.Issue;
import com.team6.issue_tracker.domain.issue.dto.*;
import com.team6.issue_tracker.domain.issue.service.IssueService;
import com.team6.issue_tracker.domain.issue.service.IssueUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;
    private final IssueUpdateService issueUpdateService;

    @Operation(
            summary = "이슈 상세 보기",
            tags = "issue",
            description = "사용자는 이슈 상세 정보를 볼 수 있다."
    )
    @GetMapping("/issue/{issueIdx}")
    public IssueDetail getIssueDetails(@PathVariable("issueIdx") long issueIdx) {
        return issueService.findById(issueIdx);
    }

    @Operation(
            summary = "이슈 작성",
            tags = "issue",
            description = "사용자는 새로운 이슈를 작성할 수 있다."
    )
    @PostMapping("/issue")
    public void postNewIssue(@RequestBody CreateIssueRequest request) {
        //TODO 유저 권한 검사
        //TODO 유효성 검사
        issueService.saveIssue(request.toIssue());
    }

    @Operation(
            summary = "여러 이슈의 상태 수정",
            tags = "issue",
            description = "사용자는 원하는 이슈를 다중 선택해 상태를 변경할 수 있다."
    )
    @PatchMapping("/issue")
    public void updateIssuesStatus(UpdateIssueListStatusRequest request) {
        //TODO 유저 권한 검사
        //TODO 유효성 검사
        issueUpdateService.updateIssueListStatus(request.getIssueIdx(), request.getStatus());
    }

    @Operation(
            summary = "이슈 상태 수정",
            tags = "issue",
            description = "사용자는 이슈 상태만 수정할 수 있다."
    )
    @PatchMapping("/issue/{issueIdx}/status")
    public void updateIssuesStatus(@RequestBody UpdateIssueStatusRequest request, @PathVariable("issueIdx") long issueIdx) {
        //TODO 유저 권한 검사
        //TODO 유효성 검사
        issueUpdateService.updateIssueStatus(issueIdx, request.getStatus());
    }

    @Operation(
            summary = "이슈 제목 수정",
            tags = "issue",
            description = "사용자는 이슈 제목만 수정할 수 있다."
    )
    @PatchMapping("/issue/{issueIdx}/title")
    public void updateIssuesTitle(@RequestBody UpdateIssueTitleRequest request, @PathVariable("issueIdx") long issueIdx) {
        //TODO 유저 권한 검사
        //TODO 유효성 검사
        issueUpdateService.updateIssueTitle(issueIdx, request.getTitle());
    }

    @Operation(
            summary = "이슈 담당자 수정",
            tags = "issue",
            description = "사용자는 이슈 담당자만 수정할 수 있다."
    )
    @PatchMapping("/issue/{issueIdx}/assignee")
    public void updateIssuesAssignee(@RequestBody UpdateIssueAssigneeRequest request, @PathVariable("issueIdx") long issueIdx) {
        //TODO 유저 권한 검사
        //TODO 유효성 검사
        issueUpdateService.updateIssueAssignee(issueIdx, request.getAssigneeIdx());
    }

    @Operation(
            summary = "이슈 내용 수정",
            tags = "issue",
            description = "사용자는 이슈를 수정할 수 있다."
    )
    @PutMapping("/issue/{issueId}")
    public void updateIssueContents(IssueDetail issueDetail) {
        //TODO 유저 권한 검사
        //TODO 유효성 검사
        Issue updatedIssue = issueDetail.fromDto(issueDetail);
        issueUpdateService.updateIssue(updatedIssue);
    }


}
