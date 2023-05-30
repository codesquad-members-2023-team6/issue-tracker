package com.team6.issue_tracker.domain.issue.service;

import com.team6.issue_tracker.domain.comment.domain.Comment;
import com.team6.issue_tracker.domain.comment.service.CommentService;
import com.team6.issue_tracker.domain.comment.dto.CommentDto;
import com.team6.issue_tracker.domain.issue.domain.Issue;
import com.team6.issue_tracker.domain.model.Status;
import com.team6.issue_tracker.domain.page.dto.IssueFilter;
import com.team6.issue_tracker.domain.issue.dto.IssueDetail;
import com.team6.issue_tracker.domain.issue.repository.IssueRepository;
import com.team6.issue_tracker.domain.label.service.LabelService;
import com.team6.issue_tracker.domain.label.dto.LabelDto;
import com.team6.issue_tracker.domain.member.service.MemberService;
import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.domain.member.dto.MemberDto;
import com.team6.issue_tracker.domain.milestone.domain.Milestone;
import com.team6.issue_tracker.domain.milestone.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    private final CommentService commentService;
    private final MemberService memberService;
    private final LabelService labelService;
    private final MilestoneService milestoneService;

    private Issue findIssueById(Long issueIdx) throws NoSuchElementException {
        Issue issue = issueRepository.findById(issueIdx).orElseThrow();

        if (issue.getIsDeleted()) {
            throw new NoSuchElementException("삭제된 이슈입니다.");
        }

        return issue;
    }

    public List<Issue> findByfilterWithPage(Integer offset, Integer pageSize, IssueFilter filter) {
        return issueRepository.findAllBy(
                filter.getIsOpen(),
                filter.getMailestone(),
                filter.getMilestoneEmptyFlag(),
                filter.getWriter(),
                filter.getAssignee(),
                filter.getAssigneeEmptyFlag(),
                filter.getLabel(),
                filter.getLabelEmptyFlag(),
                pageSize,
                offset
        );
    }

    public IssueDetail findById(Long issueIdx){
        Issue issue = findIssueById(issueIdx);

        List<Comment> comments = commentService.getCommentsOnIssue(issueIdx);
        Map<Long, Member> members = memberService.findMembers(issue.getWriter(), issue.getAssignee());

        Member writer = members.get(issue.getWriter().getId());

        Member assignee = getAssignee(members, issue);

        Milestone milestone = getMilestone(issue);

        List<LabelDto> labelDtoList = new ArrayList<>();
        labelService.findAllById(issue.getLabelOnIssue().values())
                .forEach(l -> labelDtoList.add(LabelDto.of(l)));

        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            Member commentWriter = memberService.findById(comment.getCreatedBy().getId());
            CommentDto dto = CommentDto.fromComment(comment, commentWriter);
            commentDtos.add(dto);
        }

        return IssueDetail.toDetails(issue, MemberDto.from(writer), MemberDto.from(assignee), labelDtoList, milestone, commentDtos);
    }

    private Member getAssignee(Map<Long, Member> members, Issue issue) {
        Member assginee = null;
        if (issue.getAssignee() != null) {
            assginee = members.get(issue.getAssignee().getId());
        }
        return assginee;
    }

    private Milestone getMilestone(Issue issue) {
        Milestone milestone = null;
        if (issue.getMilestone() != null) {
            milestone =milestoneService.findById(issue.getMilestone().getId());
        }
        return milestone;
    }

    public void saveIssue(Issue toIssue) {
        issueRepository.save(toIssue);
    }

    public long getIssueNum(Status status) {
        if (status==Status.OPEN) {
            return issueRepository.countAllByIsDeletedFalseAndIsOpen(true);
        }
        return issueRepository.countAllByIsDeletedFalseAndIsOpen(false);
    }
}
