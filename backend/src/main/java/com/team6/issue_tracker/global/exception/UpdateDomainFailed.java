package com.team6.issue_tracker.global.exception;

public class UpdateDomainFailed extends RuntimeException {
    public UpdateDomainFailed(String message) {
        super(message);
    }
}
