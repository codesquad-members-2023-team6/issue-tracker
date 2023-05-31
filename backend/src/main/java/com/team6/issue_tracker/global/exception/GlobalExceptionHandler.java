package com.team6.issue_tracker.global.exception;

import com.team6.issue_tracker.domain.page.exception.NoPageSearchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoPageSearchElementException.class)
    public ResponseErrorFormat handleBadRequestException (NoPageSearchElementException e) {
        log.error(e.getMessage());
        return ResponseErrorFormat.of(e, e.getMessage());
    }

}
