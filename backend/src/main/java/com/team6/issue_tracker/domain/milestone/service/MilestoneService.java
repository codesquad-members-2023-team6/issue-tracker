package com.team6.issue_tracker.domain.milestone.service;

import com.team6.issue_tracker.domain.milestone.domain.Milestone;
import com.team6.issue_tracker.domain.milestone.dto.MilestoneDetail;
import com.team6.issue_tracker.domain.milestone.repository.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    public Map<Long, Milestone> getAllMilestones() {
        List<Milestone> milestones = milestoneRepository.findAllByIsDeletedFalse();

        Map<Long, Milestone> milestoneMap = new HashMap<>();
        milestones.forEach(m -> milestoneMap.put(m.getMilestoneIdx(), m));
        return milestoneMap;
    }

    public Milestone findById(Long id) throws NoSuchElementException {
        Milestone milestone = milestoneRepository.findById(id).orElseThrow();

        if (milestone.getIsDeleted()) {
            throw new NoSuchElementException("삭제된 마일스톤입니다.");
        }

        return milestone;
    }

    public List<MilestoneDetail> getAllMilestonesWithIssueNum() {
        return milestoneRepository.findAllMilestonesWithIssueCount().stream().map(MilestoneDetail::fromMilestone)
                .collect(Collectors.toList());
    }

    public MilestoneDetail findByIdWithIssueCount(Long id) {
        return MilestoneDetail.fromMilestone(milestoneRepository.findMilestoneWithIssueCount(id));
    }
}
