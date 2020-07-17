package com.poc.restunittest.exception;

import com.poc.restunittest.exception.model.entity.CustomErrorResponse;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.poc.restunittest.exception.constant.ErrorCode.*;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = date.format(format);
        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                .code(CODE_NOT_FOUND_EMPLOYEE)
                .reason(CODE_NOT_FOUND_EMPLOYEE_REASON)
                .timestamp(timestamp)
                .build();
        return handleExceptionInternal(ex, customErrorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = date.format(format);
        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                .code(CODE_MISSING_FIELDS)
                .reason(CODE_MISSING_FIELDS_REASON)
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
