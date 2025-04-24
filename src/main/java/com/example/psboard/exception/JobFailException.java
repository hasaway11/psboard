package com.example.psboard.exception;

import lombok.*;

@Getter
@AllArgsConstructor
public class JobFailException extends RuntimeException {
  private String message;
}
