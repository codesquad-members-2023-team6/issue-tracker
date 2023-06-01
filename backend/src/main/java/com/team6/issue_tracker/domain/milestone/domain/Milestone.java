package com.team6.issue_tracker.domain.milestone.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor
public class Milestone {
    @Id
    private Long milestoneIdx;
    private String title;
    private Boolean isOpen;
    private Instant endedAt;
    private String contents;
    private Boolean isDeleted;

    public Milestone(String title, Instant endedAt, String contents) {
        this.title = title;
        this.endedAt = endedAt;
        this.contents = contents;
    }

    @PersistenceCreator
    public Milestone(Long milestoneIdx, String title, Boolean isOpen, Instant endedAt, String contents, Boolean isDeleted) {
        this.milestoneIdx = milestoneIdx;
        this.title = title;
        this.isOpen = isOpen;
        this.endedAt = endedAt;
        this.contents = contents;
        this.isDeleted = isDeleted;
    }

    public static Milestone newMilestone(String title, LocalDateTime endedAt, String contents) {
        return new Milestone(title, Instant.parse(endedAt.toString()), contents);
    }
}
