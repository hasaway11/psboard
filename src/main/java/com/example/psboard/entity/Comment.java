package com.example.psboard.entity;

import lombok.*;

import java.time.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
  private Integer cno;
  private String content;
  @Builder.Default
  private LocalDateTime writeTime = LocalDateTime.now();
  private String writer;
  private Integer bno;
}
