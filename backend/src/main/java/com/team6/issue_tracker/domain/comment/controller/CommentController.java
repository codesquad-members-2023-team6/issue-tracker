package com.team6.issue_tracker.domain.comment.controller;

import com.team6.issue_tracker.domain.comment.dto.CommentDto;
import com.team6.issue_tracker.domain.comment.service.CommentService;
import com.team6.issue_tracker.domain.comment.domain.Comment;
import com.team6.issue_tracker.domain.comment.dto.CreateCommentRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/issue/{issueIdx}")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(
            summary = "코멘트 작성",
            tags = "comment",
            description = "사용자는 코멘트를 등록할 수 있다."
    )
    @PostMapping("/comment")
    public void createComment(@PathVariable("issueIdx") long issueIdx, @RequestBody CreateCommentRequest request) {
        //TODO 유효성 검증
        commentService.saveComment(Comment.newComment(request.getContents(), issueIdx, request.getWriterIdx()));
    }

    @Operation(
            summary = "코멘트 수정",
            tags = "comment",
            description = "사용자는 코멘트를 수정할 수 있다."
    )
    @PutMapping("/comment/{commentIdx}")
    public void updateComment(@PathVariable("issueIdx") long issueIdx,
                              @PathVariable("commentIdx") long commentIdx,
                              @RequestBody CommentDto commentDto) {
        //TODO 작성자 검증
        //TODO 유효성 검증
        commentService.saveComment(commentDto.toUpdatedComment(issueIdx));
    }

    @Operation(
            summary = "코멘트 삭제",
            tags = "comment",
            description = "사용자는 코멘트를 삭제할 수 있다."
    )
    @DeleteMapping("/comment/{commentIdx}")
    public void deleteComment(@PathVariable("commentIdx") long commentIdx) {
        //TODO 작성자 검증
        //TODO 유효성 검증
        commentService.deleteComment(commentIdx);
    }
}
