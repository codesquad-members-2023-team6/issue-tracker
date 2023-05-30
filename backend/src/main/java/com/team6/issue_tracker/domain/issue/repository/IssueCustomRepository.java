package com.team6.issue_tracker.domain.issue.repository;

import com.team6.issue_tracker.domain.issue.domain.Issue;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueCustomRepository extends CrudRepository<Issue, Long> {
    @Query(value = "SELECT DISTINCT i.issue_idx, i.title, i.contents, i.is_open, i.created_at, i.edited_at," +
            "i.milestone, i.writer, i.assignee, i.is_deleted " +
            "FROM issue i " +
            "LEFT OUTER JOIN labeling il ON i.issue_idx = il.issue_idx " +
            "WHERE (:isOpen IS NULL OR i.is_open = :isOpen) " +
            "AND (:milestoneIdx IS NULL OR i.milestone = :milestoneIdx) " +
            "AND (:milestoneEmptyFlag IS NULL OR i.milestone is null ) " +
            "AND (:writer IS NULL OR i.writer = :writer) " +
            "AND (:assignee IS NULL OR i.assignee = :assignee) " +
            "AND (:assigneeEmptyFlag IS NULL OR i.assignee is null ) " +
            "AND (:labelIdx IS NULL OR il.label_idx in (:labelIdx)) " +
            "AND (:labelEmptyFlag IS NULL OR il.label_idx is null ) " +
            "AND i.is_deleted = false " +
            "ORDER BY i.issue_idx DESC " +
            "LIMIT :pageSize OFFSET :offset")
    List<Issue> findAllBy(@Param("isOpen") boolean isOpen,
                          @Param("milestoneIdx") Long milestoneIdx,
                          @Param("milestoneEmptyFlag") Boolean milestoneEmptyFlag,
                          @Param("writer") Long writer,
                          @Param("assignee") Long assignee,
                          @Param("assigneeEmptyFlag") Boolean assigneeEmptyFlag,
                          @Param("labelIdx") List<Long> labelIdx,
                          @Param("labelEmptyFlag") Boolean labelEmptyFlag,
                          @Param("pageSize") int pageSize,
                          @Param("offset") int offset);
}
