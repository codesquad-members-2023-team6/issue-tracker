package com.team6.issue_tracker.global.exception;

import lombok.Getter;

@Getter
public class ResponseError {
    private String error;
    private String message;

    private ResponseError(Exception error, String message) {
        this.error = error.toString();
        this.message = message;
    }

    public static ResponseError of(Exception error, String message) {
       return new ResponseError(error, message);
   }
}
