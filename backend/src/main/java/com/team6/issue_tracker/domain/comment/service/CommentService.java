package com.team6.issue_tracker.domain.comment.service;

import com.team6.issue_tracker.domain.comment.domain.Comment;
import com.team6.issue_tracker.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getCommentsOnIssue(Long issueIdx) {
        return commentRepository.findAllByIssueIdx(AggregateReference.to(issueIdx));
    }

    public void saveComment(Comment newComment) {
        commentRepository.save(newComment);
    }

    public void deleteComment(long commentIdx) {
        commentRepository.softDeleteById(commentIdx);
    }

}
