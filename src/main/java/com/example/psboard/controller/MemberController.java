package com.example.psboard.controller;

import com.example.psboard.dto.*;
import com.example.psboard.entity.*;
import com.example.psboard.service.*;
import io.swagger.v3.oas.annotations.*;
import jakarta.servlet.http.*;
import jakarta.validation.*;

import lombok.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;
import java.util.*;

@Validated
@RestController
@RequiredArgsConstructor
public class MemberController {
  private final MemberService service;

  @Operation(summary="아이디 사용여부", description="아이디가 사용가능한 지 확인")
  @PreAuthorize("isAnonymous()")
  @GetMapping(value="/api/members/check-username")
  public ResponseEntity<String> checkUsername(@ModelAttribute @Valid MemberDto.UsernameCheckRequest dto, BindingResult br) {
    boolean result = service.checkUsername(dto);
    if (result)
      return ResponseEntity.ok("사용가능한 아이디입니다");
    return ResponseEntity.status(HttpStatus.CONFLICT).body("사용할 수 없습니다 ");
  }

  @Operation(summary="회원가입", description="회원가입 및 프로필 사진 업로드")
  @PreAuthorize("isAnonymous()")
  @PostMapping(value = "/api/members/new", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Member> signUp(@ModelAttribute @Valid MemberDto.SignUpRequest dto, BindingResult br) {
    return ResponseEntity.ok(service.signUp(dto));
  }

  @Operation(summary="아이디 찾기", description="이메일로 아이디를 찾는다")
  @PreAuthorize("isAnonymous()")
  @PostMapping(value = "/api/members/username")
  public ResponseEntity<String> searchUsername(@ModelAttribute @Valid MemberDto.UsernameSearchRequest dto, BindingResult br) {
    return ResponseEntity.ok(service.searchUsername(dto));
  }

  @Operation(summary="비밀번호 확인 (*)", description="자신의 정보를 보기 위한 비밀번호 확인")
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/api/members/verify-password")
  public ResponseEntity<String> verifyPassword(@ModelAttribute @Valid MemberDto.PasswordCheckRequest dto, BindingResult br, Principal principal) {
    boolean result = service.verifyPassword(dto, principal.getName());
    if(result)
      return ResponseEntity.ok("비밀번호를 확인했습니다");
    return ResponseEntity.status(HttpStatus.CONFLICT).body("잘못된 비밀번호입니다");
  }

  @Operation(summary = "내 정보 보기 (*)", description = "비밀번호 확인 후 자신의 정보 보기")
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/api/members/member")
  public ResponseEntity<MemberDto.Read> readme(Principal principal) {
    return ResponseEntity.ok(service.read(principal.getName()));
  }

  @Operation(summary = "비밀번호 변경 (*)", description = "비밀번호 변경")
  @PreAuthorize("isAuthenticated()")
  @PatchMapping("/api/members/change-password")
  public ResponseEntity<String> chenagePassword(@ModelAttribute @Valid MemberDto.PasswordChangeRequest dto, BindingResult br, Principal principal) {
    service.changePassword(dto, principal.getName());
    return ResponseEntity.ok("비밀번호를 변경했습니다");
  }

  @Operation(summary = "회원 탈퇴 (*)", description = "회원 탈퇴")
  @PreAuthorize("isAuthenticated()")
  @PatchMapping("/api/members/resign")
  public ResponseEntity<String> resign(Principal principal, HttpSession session) {
    boolean result = service.resign(principal.getName());
    if(!result)
      return ResponseEntity.status(HttpStatus.CONFLICT).body("작업에 문제가 발생했습니ㄷ");
    session.invalidate();
    return ResponseEntity.ok("로그아웃되었습니다");
  }
}
