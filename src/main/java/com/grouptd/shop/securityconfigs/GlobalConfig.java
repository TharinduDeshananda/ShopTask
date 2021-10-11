package com.grouptd.shop.securityconfigs;

import com.grouptd.shop.models.ErrorResponse;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalConfig {




    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return ResponseEntity.ok("Method Arguments are not valid");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity noHandlerFound(){
        System.out.println("No handler found");
       return ResponseEntity.ok(new ArrayList<>());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity accessDenied(){
        System.out.println("Access denied with exception handler");
        return ResponseEntity.ok("access denied");
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    public ResponseEntity handleGeneralException(Exception exception){
        System.out.println("******");
        return ResponseEntity.ok(new ErrorResponse(exception.getMessage()));
    }

}
