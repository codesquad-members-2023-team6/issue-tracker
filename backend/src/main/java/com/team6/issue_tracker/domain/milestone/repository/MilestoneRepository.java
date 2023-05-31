package com.team6.issue_tracker.domain.milestone.repository;

import com.team6.issue_tracker.domain.milestone.domain.Milestone;
import com.team6.issue_tracker.domain.milestone.domain.MilestoneWithIssueCount;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface MilestoneRepository extends CrudRepository<Milestone, @NotNull Long> {

    List<Milestone> findAllByIsDeletedFalse();

    @Query("SELECT m.milestone_idx, m.title, m.contents, m.ended_at, m.is_open, " +
            "count(i.issue_idx) as total_issue_num, " +
            "count(CASE WHEN i.is_open = FALSE THEN i.issue_idx END) as closed_issue_num " +
            "FROM milestone m LEFT JOIN issue i on m.milestone_idx = i.milestone " +
            "where m.is_deleted = false " +
            "and i.is_deleted = false " +
            "GROUP BY m.milestone_idx")
    List<MilestoneWithIssueCount> findAllMilestonesWithIssueCount();

    @Query("SELECT m.milestone_idx, m.title, m.contents, m.ended_at, m.is_open, " +
            "count(i.issue_idx) as total_issue_num, " +
            "count(CASE WHEN i.is_open = FALSE THEN i.issue_idx END) as closed_issue_num " +
            "FROM milestone m LEFT JOIN issue i on m.milestone_idx = i.milestone " +
            "where m.is_deleted = false " +
            "and i.is_deleted = false " +
            "and m.milestone_idx = :milestoneIdx ")
    MilestoneWithIssueCount findMilestoneWithIssueCount(@Param("milestoneIdx") long milestoneIdx);

}
