package com.example.psboard.dto;

import com.example.psboard.entity.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDto {
  @Data
  public static class Create {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    public Post toEntity(String loginId) {
      return Post.builder().title(title).content(content).writer(loginId).build();
    }
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
