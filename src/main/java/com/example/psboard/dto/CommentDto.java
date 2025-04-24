package com.example.psboard.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentDto {
  @Data
  public static class Craete {
    @NotNull
    private Integer pno;
    @NotEmpty
    private String content;
  }
}
