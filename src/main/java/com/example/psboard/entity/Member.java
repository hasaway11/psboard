package com.example.psboard.entity;

import com.example.psboard.dto.*;
import com.example.psboard.util.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.*;
import java.time.temporal.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Member {
  private String username;
  @JsonIgnore
  private String password;
  private String email;
  private String profile;
  @Builder.Default
  private LocalDate joinDay = LocalDate.now();
  @Builder.Default
  private Role role = Role.USER;
  @Builder.Default
  private Level level = Level.NORMAL;
  @Builder.Default
  private boolean isLock = false;
  @Builder.Default
  private int failedAttempts = 0;

  public MemberDto.Read toRead() {
    long days = ChronoUnit.DAYS.between(joinDay, LocalDate.now());
    return new MemberDto.Read(username, email, joinDay, days, role.name(), level.name(), profile);
  }
}
