package com.team6.issue_tracker.domain.label.service;

import com.team6.issue_tracker.domain.issue.domain.Labeling;
import com.team6.issue_tracker.domain.label.domain.Label;
import com.team6.issue_tracker.domain.label.dto.LabelDetail;
import com.team6.issue_tracker.domain.label.dto.LabelSummary;
import com.team6.issue_tracker.domain.label.repository.LabelRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LabelService {

    private final LabelRepository labelRepository;

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Map<Long, LabelSummary> getAllLabelSummaries() {
        Map<Long, LabelSummary> labels = new HashMap<>();
        labelRepository.findAllByIsDeletedFalse().forEach(label -> labels.put(label.getLabelIdx(), LabelSummary.of(label)));
        return labels;
    }

    public Iterable<Label> findAllById(Collection<Labeling> values) {
        return labelRepository.findAllByLabelIdxInAndAndIsDeleted(values.stream()
                .map(Labeling::getLabelIdx)
                .collect(Collectors.toList()), false);
    }

    public List<LabelDetail> getAllLabelDetails() {
        List<LabelDetail> list = new ArrayList<>();
        labelRepository.findAllByIsDeletedFalse().forEach(label -> list.add(LabelDetail.of(label)));
        return list;
    }
}
