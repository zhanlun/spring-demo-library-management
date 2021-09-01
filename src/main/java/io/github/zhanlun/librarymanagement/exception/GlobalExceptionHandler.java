package io.github.zhanlun.librarymanagement.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Provides handling for exceptions throughout this service.
     */
    @ExceptionHandler({BadRequestException.class, NotFoundException.class})
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof BadRequestException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            BadRequestException bre = (BadRequestException) ex;
            return handleSimpleException(bre, headers, status, request);
        } else if (ex instanceof NotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            NotFoundException nfe = (NotFoundException) ex;
            return handleSimpleException(nfe, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    /**
     * Customize the response for BadRequestException.
     */
    protected ResponseEntity<ApiError> handleSimpleException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }

    /**
     * A single place to customize the response body of all Exception types.
     */
    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}