package com.team6.issue_tracker.domain.issue.domain;

import com.team6.issue_tracker.domain.label.domain.Label;
import lombok.Value;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import javax.validation.constraints.NotNull;

@Value
public class Labeling {
    AggregateReference<Label, @NotNull Long> labelIdx;

    public Labeling(AggregateReference<Label, @NotNull Long> labelIdx) {
        this.labelIdx = labelIdx;
    }

    public Long getLabelIdx() {
        return labelIdx.getId();
    }
}
