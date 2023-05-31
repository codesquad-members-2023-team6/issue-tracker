package com.team6.issue_tracker.domain.comment.repository;

import com.team6.issue_tracker.domain.comment.domain.Comment;
import com.team6.issue_tracker.domain.issue.domain.Issue;
import com.team6.issue_tracker.global.util.SoftDeleteCrudRepository;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>, SoftDeleteCrudRepository<Comment, Long> {

    List<Comment> findAllByIssueIdx(AggregateReference<Issue, @NotNull Long> issueIdx);

    @Override
    @Query("update comment e set e.is_deleted=true where e.comment_idx=:id")
    void softDeleteById(@Param("id") Long id);

    @Override
    @Query("select comment_idx, contents, created_at, edited_at, issue_idx, created_by from comment e where e.is_deleted=false")
    List<Comment> findAll();
}
