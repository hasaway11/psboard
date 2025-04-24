package com.example.psboard.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDto {
  @Data
  public static class Create {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
  }

  @Data
  public static class Update {
    @NotNull
    private Integer pno;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
  }
}
