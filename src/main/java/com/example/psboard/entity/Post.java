package com.example.psboard.entity;

import lombok.*;

import java.time.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Post {
  @Setter
  private Integer pno;
  private String title;
  private String content;
  @Builder.Default
  private LocalDateTime writeTime = LocalDateTime.now();
  private String writer;
  @Builder.Default
  private Integer readCnt = 0;
  @Builder.Default
  private Integer goodCnt = 0;
  @Builder.Default
  private Integer badCnt = 0;
}
