package com.team6.issue_tracker.domain.label.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@NoArgsConstructor
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
    public Label(Long labelIdx, String title, String description, String backgroundColor, String style, Boolean isDeleted) {
        this.labelIdx = labelIdx;
        this.title = title;
        this.description = description;
        this.backgroundColor = backgroundColor;
        this.style = style;
        this.isDeleted = isDeleted;
    }


    public Label(String title, String description, String backgroundColor, String style) {
        this.title = title;
        this.description = description;
        this.backgroundColor = backgroundColor;
        this.style = style;
    }

    public static Label newLabel(String title, String description, String backgroundColor, String style) {
        return new Label(title, description, backgroundColor, style);
    }
}
