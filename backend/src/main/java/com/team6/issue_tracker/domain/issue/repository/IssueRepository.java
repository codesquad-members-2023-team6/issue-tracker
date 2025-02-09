package com.team6.issue_tracker.domain.issue.repository;

import com.team6.issue_tracker.domain.issue.domain.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends PagingAndSortingRepository<Issue, Long>, IssueCustomRepository {

    Integer countAllByIsDeletedFalseAndIsOpen(boolean isOpen);

    Page<Issue> findAllByIsOpenAndIsDeleted(boolean isOpen, boolean isDeleted, Pageable pageable);

    @Modifying
    @Query("UPDATE issue SET is_open = :isOpen WHERE issue_idx IN (:issueIdx)")
    boolean updateIssuesIsOpen (@Param("isOpen") boolean isOpen, @Param("issueIdx") List<Long> idx);

    @Modifying
    @Query("UPDATE issue SET is_open = :isOpen WHERE issue_idx = :issueIdx")
    boolean updateIssueIsOpen(@Param("isOpen") boolean isOpen, @Param("issueIdx") long index);

    @Modifying
    @Query("UPDATE issue SET title = :title WHERE issue_idx = :issueIdx")
    boolean updateIssueTitle(@Param("title") String title, @Param("issueIdx") Long issueIdx);

    @Modifying
    @Query("UPDATE issue SET assignee = :assignee WHERE issue_idx = :issueIdx")
    boolean updateIssueAssignee(@Param("assignee") Long assignee, @Param("issueIdx") Long issueIdx);

    @Modifying
    @Query("UPDATE issue SET milestone = :milestoneIdx WHERE issue_idx = :issueIdx")
    boolean updateIssueMilestone(@Param("milestoneIdx") Long milestoneIdx, @Param("issueIdx") Long  issueIdx);
}
