package com.team6.issue_tracker.domain.label.dto;

import com.team6.issue_tracker.domain.label.domain.Label;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LabelSummary {
    private Long labelIdx;
    private String title;
    private String backgroundColor;
    private String style;

    public static LabelSummary of(Label label) {
        return LabelSummary.builder()
                .labelIdx(label.getLabelIdx())
                .title(label.getTitle())
                .backgroundColor(label.getBackgroundColor())
                .style(label.getStyle())
                .build();
    }
}
