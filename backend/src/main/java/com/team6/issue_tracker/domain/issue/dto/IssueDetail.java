package com.team6.issue_tracker.domain.issue.dto;

import com.team6.issue_tracker.domain.comment.dto.CommentDto;
import com.team6.issue_tracker.domain.issue.domain.Issue;
import com.team6.issue_tracker.domain.issue.domain.Labeling;
import com.team6.issue_tracker.domain.label.dto.LabelDto;
import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.domain.member.dto.MemberDto;
import com.team6.issue_tracker.domain.milestone.domain.Milestone;
import com.team6.issue_tracker.domain.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@Builder
@AllArgsConstructor
public class IssueDetail {

    private Long index;
    private String title;
    private String contents;
    private MemberDto writer;
    private MemberDto assignee;
    private Status status;
    private Instant createdAt;
    private Instant editedAt;
    //TODO milestone dto로 바꾸기
    private Milestone milestone;
    private List<LabelDto> labelList;
    private List<CommentDto> commentList;

    public static IssueDetail toDetails(Issue issue, MemberDto writer, MemberDto assignee,
                                        List<LabelDto> labels, Milestone milestone,
                                        List<CommentDto> coments) {
        return IssueDetail.builder()
                .index(issue.getIssueIdx())
                .title(issue.getTitle())
                .contents(issue.getContents())
                .writer(writer)
                .assignee(assignee)
                .status(Status.of(issue.getIsOpen()))
                .createdAt(issue.getCreatedAt())
                .labelList(labels)
                .milestone(milestone)
                .commentList(coments)
                .build();
    }

    public Issue fromDto(IssueDetail dto) {
        return Issue.builder()
                .issueIdx(dto.getIndex())
                .title(dto.getTitle())
                .contents(dto.getContents())
                .writer(AggregateReference.to(dto.getWriter().getMemberIdx()))
                .assignee(nullableMember(dto.getAssignee()))
                .isOpen(dto.getStatus() == Status.OPEN)
                .createdAt(dto.getCreatedAt())
                .labelOnIssue(getLabelOnIssue(dto.getLabelList()))
                .milestone(nullableMilestone(dto.getMilestone()))
                .editedAt(Instant.now())
                .isDeleted(false)
                .build();
    }

    private AggregateReference<Milestone, Long> nullableMilestone(Milestone milestone) {
        if (milestone != null) {
            return AggregateReference.to(milestone.getMilestoneIdx());
        }
        return null;
    }

    private Map<Long, Labeling> getLabelOnIssue(List<LabelDto> labelList) {
        Map<Long, Labeling> labelingMap = new HashMap<>();
        labelList.forEach(l -> labelingMap.put(l.getLabelIdx(), new Labeling(l.getLabelIdx())));
        return labelingMap;
    }

    private AggregateReference<Member, @NotNull Long> nullableMember(MemberDto m) {
        if (m != null) {
            return AggregateReference.to(m.getMemberIdx());
        }
        return null;
    }
}
