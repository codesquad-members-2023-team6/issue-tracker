package com.team6.issue_tracker.domain.label.repository;

import com.team6.issue_tracker.domain.label.domain.Label;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelRepository extends CrudRepository<Label, Long> {
    Iterable<Label> findAllByIsDeletedFalse();
    Iterable<Label> findAllByLabelIdxInAndIsDeleted(List<Long> labelIdx, boolean isDeleted);
}
