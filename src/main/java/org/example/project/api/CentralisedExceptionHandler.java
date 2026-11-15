package org.example.project.api;


import jakarta.servlet.http.HttpServletRequest;
import org.example.project.service.AppUserNotFoundException;
import org.example.project.service.BusinessRuleException;
import org.example.project.service.DuplicateJokeException;
import org.example.project.service.JokeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CentralisedExceptionHandler {
    private Map<String, String> createResponse(
            String message,
            String status,
            HttpServletRequest request
    ) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status);
        response.put("time", LocalDateTime.now().toString());
        response.put("request", request.getRequestURI());
        response.put("method", request.getMethod());
        return response;
    }

    @ExceptionHandler(JokeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(
            JokeNotFoundException e,
            HttpServletRequest request
    ) {
        return createResponse(e.getMessage(), "404", request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMalformedJson(
            HttpMessageNotReadableException e,
            HttpServletRequest request
    ) {
        return createResponse("Request body is missing or contains malformed JSON", "400", request);
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidRequest(
            Exception e,
            HttpServletRequest request
    ) {
        return createResponse("Invalid request parameters or fields", "400", request);
    }

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, String> handleBusinessRule(
            BusinessRuleException e,
            HttpServletRequest request
    ) {
        return createResponse(e.getMessage(), "422", request);
    }

    @ExceptionHandler(DuplicateJokeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleDuplicate(
            DuplicateJokeException e,
            HttpServletRequest request
    ) {
        return createResponse(e.getMessage(), "409", request);
    }

    @ExceptionHandler(AppUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleUserNotFound(
            AppUserNotFoundException e,
            HttpServletRequest request
    ) {
        return createResponse(e.getMessage(), "404", request);
    }
}
