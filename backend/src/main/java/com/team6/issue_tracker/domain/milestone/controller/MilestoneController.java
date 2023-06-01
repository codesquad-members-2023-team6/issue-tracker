package com.team6.issue_tracker.domain.milestone.controller;

import com.team6.issue_tracker.domain.milestone.dto.MilestoneDetail;
import com.team6.issue_tracker.domain.milestone.service.MilestoneService;
import com.team6.issue_tracker.global.util.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @Operation(
            summary = "마일스톤 목록",
            tags = "milestone",
            description = "사용자는 마일스톤 목록을 볼 수 있다."
    )
    @GetMapping("/milestones")
    public ResponseEntity<ResponseMessage<List<MilestoneDetail>>> findAll() {
        List<MilestoneDetail> milestoneDetails = milestoneService.getAllMilestonesWithIssueNum();
        return ResponseMessage.of(HttpStatus.OK, "Milestone details retrieved successfully", milestoneDetails);
    }
}
