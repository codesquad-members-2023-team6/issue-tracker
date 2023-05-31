package com.team6.issue_tracker.domain.member.controller;

import com.team6.issue_tracker.domain.member.dto.MemberDto;
import com.team6.issue_tracker.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(
            summary = "멤버 목록",
            tags = "member",
            description = "사용자는 회원 목록을 볼 수 있다."
    )
    @GetMapping("/members")
    public List<MemberDto> findAll() {
        return new ArrayList<>(memberService.getAllMembers().values());
    }

}
