package com.team6.issue_tracker.domain.page.controller;

import com.team6.issue_tracker.domain.page.dto.IssuePageRequest;
import com.team6.issue_tracker.domain.page.dto.IssuePageResponse;
import com.team6.issue_tracker.domain.page.exception.NoPageSearchElementException;
import com.team6.issue_tracker.domain.page.service.PageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    @Operation(
            summary = "이슈 목록",
            tags = "issue page",
            description = "사용자는 이슈 목록을 필터링하여 볼 수 있다. page, status 는 필수 파라미터입니다."
    )
    @GetMapping("/issue")
    public IssuePageResponse findAllIssueOpenList(IssuePageRequest requestParams) throws NoPageSearchElementException {
        if (requestParams.getPage()== null || requestParams.getStatus()==null) {
            throw new NoPageSearchElementException("필수 파라미터 page, status 와 함께 요청해주세요.");
        }
        return pageService.getAPage(requestParams.getPage(), requestParams.toFilter());
    }
}
