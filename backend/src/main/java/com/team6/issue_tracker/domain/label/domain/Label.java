package com.team6.issue_tracker.domain.label.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@Table("label")
public class Label {
    @Id
    private Long labelIdx;

    private String title;

    private String description;

    private String backgroundColor;

    private String style;

    private Boolean isDeleted;

    @PersistenceCreator
    public Label(Long labelIdx, String title, String description, String backgroundColor, String textColor, Boolean isDeleted) {
        this.labelIdx = labelIdx;
        this.title = title;
        this.description = description;
        this.backgroundColor = backgroundColor;
        this.style = textColor;
        this.isDeleted = isDeleted;
    }

    private Label(String title, String description, String backgroundColor, String textColor, Boolean isDeleted) {
        this.title = title;
        this.description = description;
        this.backgroundColor = backgroundColor;
        this.style = textColor;
        this.isDeleted = isDeleted;
    }

    public static Label newLabel(String title, String description, String backgroundColor, String textColor) {
        return new Label(title, description, backgroundColor, textColor, false);
    }
}
