//package com.alexahdp.spring.exceptionHandlers;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Slf4j
//@ControllerAdvice(basePackages = "com.alexahdp.spring.controllers")
//public class ControllerExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public String handleExceptions(Exception e, HttpServletRequest request) {
////        log.error("Request: " + request.getRequestURI());
//        log.error("Failed to return response", e);
//        return e.getMessage();
//    }
//}
