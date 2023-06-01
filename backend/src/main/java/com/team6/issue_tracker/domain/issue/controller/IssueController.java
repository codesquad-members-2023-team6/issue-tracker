package com.team6.issue_tracker.domain.issue.controller;

import com.team6.issue_tracker.domain.issue.domain.Issue;
import com.team6.issue_tracker.domain.issue.dto.*;
import com.team6.issue_tracker.domain.issue.service.IssueService;
import com.team6.issue_tracker.domain.issue.service.IssueUpdateService;
import com.team6.issue_tracker.global.util.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseMessage<IssueDetail>> getIssueDetails(@PathVariable("issueIdx") long issueIdx) {
        IssueDetail issueDetail = issueService.findById(issueIdx);
        return ResponseMessage.of(HttpStatus.OK, "Issue details retrieved successfully", issueDetail);
    }

    @Operation(
            summary = "이슈 작성",
            tags = "issue",
            description = "사용자는 새로운 이슈를 작성할 수 있다."
    )
    @PostMapping("/issue")
    public ResponseEntity<ResponseMessage<Void>> postNewIssue(@RequestBody CreateIssueRequest request) {
        issueService.saveIssue(request.toIssue());
        return ResponseMessage.of(HttpStatus.CREATED, "New issue created successfully", null);
    }

    @Operation(
            summary = "여러 이슈의 상태 수정",
            tags = "issue",
            description = "사용자는 원하는 이슈를 다중 선택해 상태를 변경할 수 있다."
    )
    @PatchMapping("/issue")
    public ResponseEntity<ResponseMessage<Void>> updateIssuesStatus(UpdateIssueListStatusRequest request) {
        //TODO 유효성 검사
        log.debug(request.toString());
        issueUpdateService.updateIssueListStatus(request.getIssueIdx(), request.getStatus());
        return ResponseMessage.of(HttpStatus.OK, "Issue status updated successfully", null);
    }

    @Operation(
            summary = "이슈 상태 수정",
            tags = "issue",
            description = "사용자는 이슈 상태만 수정할 수 있다."
    )
    @PatchMapping("/issue/{issueIdx}/status")
    public ResponseEntity<ResponseMessage<Void>> updateIssuesStatus(@RequestBody UpdateIssueStatusRequest request, @PathVariable("issueIdx") long issueIdx) {
        //TODO 유효성 검사
        issueUpdateService.updateIssueStatus(issueIdx, request.getStatus());
        return ResponseMessage.of(HttpStatus.OK, "Issue status updated successfully", null);
    }

    @Operation(
            summary = "이슈 제목 수정",
            tags = "issue",
            description = "사용자는 이슈 제목만 수정할 수 있다."
    )
    @PatchMapping("/issue/{issueIdx}/title")
    public ResponseEntity<ResponseMessage<Void>> updateIssuesTitle(@RequestBody UpdateIssueTitleRequest request, @PathVariable("issueIdx") long issueIdx) {
        //TODO 유효성 검사
        issueUpdateService.updateIssueTitle(issueIdx, request.getTitle());
        return ResponseMessage.of(HttpStatus.OK, "Issue title updated successfully", null);
    }

    @Operation(
            summary = "이슈 담당자 수정",
            tags = "issue",
            description = "사용자는 이슈 담당자만 수정할 수 있다."
    )
    @PatchMapping("/issue/{issueIdx}/assignee")
    public ResponseEntity<ResponseMessage<Void>> updateIssuesAssignee(@RequestBody UpdateIssueAssigneeRequest request, @PathVariable("issueIdx") long issueIdx) {
        //TODO 유효성 검사
        issueUpdateService.updateIssueAssignee(issueIdx, request.getAssigneeIdx());
        return ResponseMessage.of(HttpStatus.OK, "Issue assignee updated successfully", null);
    }

    @Operation(
            summary = "이슈 내용 수정",
            tags = "issue",
            description = "사용자는 이슈를 수정할 수 있다."
    )
    @PutMapping("/issue/{issueId}")
    public ResponseEntity<ResponseMessage<Void>> updateIssueContents(IssueDetail issueDetail) {
        //TODO 유효성 검사
        Issue updatedIssue = issueDetail.fromDto(issueDetail);
        issueUpdateService.updateIssue(updatedIssue);
        return ResponseMessage.of(HttpStatus.OK, "Issue content updated successfully", null);
    }
}
