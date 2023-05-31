package com.team6.issue_tracker.domain.label.dto;

import com.team6.issue_tracker.domain.label.domain.Label;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LabelDetail {
    private Long index;
    private String title;
    private String description;
    private String backgroundColor;
    private String style;

    public static LabelDetail of(Label label) {
        return LabelDetail.builder()
                .index(label.getLabelIdx())
                .title(label.getTitle())
                .description(label.getDescription())
                .backgroundColor(label.getBackgroundColor())
                .style(label.getStyle())
                .build();
    }
}
