package com.example.psboard.dto;

import com.example.psboard.entity.*;
import com.example.psboard.util.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.*;

import java.time.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDto {
  @Data
  public static class UsernameCheckRequest {
    @NotEmpty
    @Pattern(regexp = "^[a-z0-9]{6,10}$")
    private String username;
  }

  @Data
  public static class UsernameSearchRequest {
    @NotEmpty
    @Email
    private String email;
  }

  @Data
  public static class PasswordCheckRequest {
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{6,10}$")
    private String password;
  }

  @Data
  public static class SignUpRequest {
    @NotEmpty
    @Pattern(regexp = "^[a-z0-9]{6,10}$")
    private String username;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{6,10}$")
    private String password;
    @NotEmpty
    @Email
    private String email;
    private MultipartFile profile;

    public Member toEntity(String password, String base64Image) {
      return Member.builder().username(username).password(password).email(email).profile(base64Image).build();
    }
  }

  @Data
  public static class PasswordChangeRequest {
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{6,10}$")
    private String oldPassword;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{6,10}$")
    private String newPassword;
  }

  @Data
  @AllArgsConstructor
  public static class Read {
    private String username;
    private String email;
    private LocalDate joinDay;
    private long days;
    private String role;
    private String level;
    private String profile;
  }
}
