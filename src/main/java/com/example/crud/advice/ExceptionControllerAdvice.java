package com.example.crud.advice;

import com.example.crud.exception.CrudException;
import com.example.crud.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(CrudException.class)
    public ResponseEntity<ErrorCode>  handleException(CrudException e) {
        return new ResponseEntity<>(e.getErrorCode(), HttpStatus.NOT_FOUND);
    }


}
