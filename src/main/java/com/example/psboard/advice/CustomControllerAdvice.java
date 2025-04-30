package com.example.psboard.advice;

import com.example.psboard.exception.*;
import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.http.converter.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.*;


@ControllerAdvice
public class CustomControllerAdvice {
  // ?pno=aaa일때 이리로 온다
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<String> methodArgumentTypeMismatchException() {
    return ResponseEntity.status(HttpStatus.CONFLICT).body("올바르지 않은 접근경로입니다");
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> httpMessageNotReadableException() {
    return ResponseEntity.status(HttpStatus.CONFLICT).body("JSON 타입으로 요청하십시오");
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<String> constraintViolationException(ConstraintViolationException e) {
    String fieldName = e.getConstraintViolations().stream().findFirst().get().getPropertyPath().toString();
    String message = e.getConstraintViolations().stream().findFirst().get().getMessage();
    System.out.println(fieldName);  // save.dto.deadline
    String[] ar = fieldName.split("\\.");
    String field = ar[ar.length-1];
    return ResponseEntity.status(HttpStatus.CONFLICT).body(field + ": " + message);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> entityNotFoundException(EntityNotFoundException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }

  @ExceptionHandler(JobFailException.class)
  public ResponseEntity<String> jobFailException(JobFailException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }
}
