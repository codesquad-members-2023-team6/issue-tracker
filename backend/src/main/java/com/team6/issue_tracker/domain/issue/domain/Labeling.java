package com.team6.issue_tracker.domain.issue.domain;

import com.team6.issue_tracker.domain.label.domain.Label;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import javax.validation.constraints.NotNull;

@Value
@Builder
@AllArgsConstructor
public class Labeling {
    AggregateReference<Label, @NotNull Long> labelIdx;

    public Long getLabelIdx() {
        return labelIdx.getId();
    }
}
