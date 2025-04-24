package com.example.psboard.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDto {
  @Data
  public static class UsernameCheckRequest {
    @NotEmpty
    @Pattern(regexp = "^[A-Z0-9]{6,10}$")
    private String username;
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
    @Pattern(regexp = "^[A-Z0-9]{6,10}$")
    private String username;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{6,10}$")
    private String password;
    @NotEmpty
    @Email
    private String email;
    private MultipartFile profile;
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
}
