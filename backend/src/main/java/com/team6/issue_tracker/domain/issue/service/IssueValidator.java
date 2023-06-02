package com.team6.issue_tracker.domain.issue.service;

import com.team6.issue_tracker.domain.issue.domain.Issue;
import com.team6.issue_tracker.domain.issue.dto.CreateIssueRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class IssueValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CreateIssueRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateIssueRequest issue = (CreateIssueRequest) target;

        if (issue.getTitle()==null) {
            errors.rejectValue("title", "제목을 입력해주세요.");
        }
        if (issue.getTitle().length() > 50 || issue.getTitle().length() <1) {
            errors.rejectValue("title", "제목을 1~50자 이내로 작성해주세요.");
        }
    }
}
