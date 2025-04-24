package com.example.psboard.exception;

import lombok.*;

@AllArgsConstructor
@Getter
public class EntityNotFoundException extends RuntimeException {
  private String message;
}
