package com.springboot.ecom.config;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ){
        System.out.println("I am spring, and I m in handler method");
        BindingResult result =  e.getBindingResult();
        List<FieldError> list =  result.getFieldErrors();
        Map<String, String> map = new HashMap<>();
        list.forEach(err->{
            map.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity
                .badRequest()
                .body(map);

    }
}
