package com.vk.user.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.BindException;

@Log4j2
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleResourceNotFoundException(BindException ex, WebRequest request) {
        log.error("Parameter invalid - " + ex.getMessage());
        return ResponseEntity.status(400).build();
    }

}
