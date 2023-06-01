package com.team6.issue_tracker.global.exception;

import com.team6.issue_tracker.domain.page.exception.NoPageSearchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoPageSearchElementException.class)
    public ResponseError handleBadRequestException (NoPageSearchElementException e) {
        log.error(e.getMessage());
        return ResponseError.of(e, e.getMessage());
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseError handGlobalException(Exception e) {
        log.error(e.getMessage());
        return ResponseError.of(e, e.getMessage());
    }
}
