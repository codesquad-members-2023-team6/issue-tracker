package com.team6.issue_tracker.global.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Slf4j
@AllArgsConstructor
public class ResponseMessage<T> {
    private HttpStatus status;
    private String message;
    private T data;

    public static <T> ResponseEntity<ResponseMessage<T>> of(HttpStatus status, String message, T data) {
        return new ResponseEntity<>(new ResponseMessage<>(status, message, data), status);
    }

    public ResponseEntity<ResponseMessage<T>> toResponseEntityWithHeaders(HttpHeaders headers) {
        return new ResponseEntity<>(this, headers, this.status);
    }
}
