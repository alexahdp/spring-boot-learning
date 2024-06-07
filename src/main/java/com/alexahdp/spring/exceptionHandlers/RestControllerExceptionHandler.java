package com.alexahdp.spring.exceptionHandlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice(basePackages = "com.alexahdp.spring.controllers")
// extends ResponseEntityExceptionHandler
public class RestControllerExceptionHandler  {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleExceptions(Exception e, HttpServletRequest request) {
//        log.error("Request: " + request.getRequestURI());
        log.error("Failed to return response", e);
//        return new Error("An unexpected error occurred: " + e.getMessage());
//        return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        //        return ResponseEntity.status(400)
//                .body(new ApiErrorResponse(e.getResponse().getStatus(),
//                        e.getResponse().getMessage(),
//                        e.getResponse().getErrors()));
        return e.getMessage();
    }
}
