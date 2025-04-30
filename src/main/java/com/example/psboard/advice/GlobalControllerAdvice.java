package com.example.psboard.advice;

import org.springframework.http.*;
import org.springframework.web.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.resource.*;

@ControllerAdvice
public class GlobalControllerAdvice {
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<String> handle405(HttpRequestMethodNotSupportedException ex) {
    return ResponseEntity.status(405).body("허용되지 않은 메소드입니다");
  }

//  @ExceptionHandler(NoHandlerFoundException.class)
//  public ResponseEntity<String> handle404(NoHandlerFoundException ex) {
//    return ResponseEntity.status(404).body("존재하지 않는 API입니다: ");
//  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<String> handleStatic404(NoResourceFoundException ex) {
    return ResponseEntity.status(404).body("존재하지 않는 URL: " + ex.getResourcePath());
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<String> handleTypeMismatch(MissingServletRequestParameterException ex) {
    return ResponseEntity.status(400).body(ex.getMessage());
  }
}
