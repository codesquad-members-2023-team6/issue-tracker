package com.team6.issue_tracker.global.exception;

import lombok.Getter;

@Getter
public class ResponseErrorFormat {
    private String error;
    private String message;

    private ResponseErrorFormat(Exception error, String message) {
        this.error = error.toString();
        this.message = message;
    }

    public static ResponseErrorFormat of(Exception error, String message) {
       return new ResponseErrorFormat(error, message);
   }
}
