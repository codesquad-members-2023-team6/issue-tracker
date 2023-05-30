package com.team6.issue_tracker.domain.label.dto;

import com.team6.issue_tracker.domain.label.domain.Label;
import lombok.Data;

@Data
public class LabelDto {
    private Long labelIdx;
    private String title;
    private String backgroundColor;
    private String style;

    public LabelDto(Long labelIdx, String title, String backgroundColor, String style) {
        this.labelIdx = labelIdx;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.style = style;
    }

    public static LabelDto of(Label label) {
        return new LabelDto(label.getLabelIdx(), label.getTitle(), label.getBackgroundColor(), label.getStyle());
    }
}
