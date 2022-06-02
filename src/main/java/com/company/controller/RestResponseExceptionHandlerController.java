package com.company.controller;

import com.company.exception.BadRequestException;
import com.company.exception.DontFoundUser;
import com.company.exception.ItemNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandlerController {


   @ExceptionHandler({BadRequestException.class, ItemNotFoundException.class, DontFoundUser.class})
    public ResponseEntity<?> handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
