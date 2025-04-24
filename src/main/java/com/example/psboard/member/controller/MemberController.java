package com.example.psboard.member.controller;

import com.example.psboard.dto.*;
import com.example.psboard.member.entity.*;
import com.example.psboard.member.service.*;
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

  @PreAuthorize("isAnonymous()")
  @GetMapping("/api/members/check-username")
  public ResponseEntity<Void> checkUsername(@ModelAttribute @Valid MemberDto.UsernameCheckRequest dto, BindingResult br) {
    service.checkUsername(dto);
    return ResponseEntity.ok().build();
  }

  @PreAuthorize("isAnonymous()")
  @PostMapping("/api/members/new")
  public ResponseEntity<Member> signUp(@ModelAttribute @Valid MemberDto.SignUpRequest dto, BindingResult br) {
    return ResponseEntity.ok(service.signUp(dto));
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/api/members/check-password")
  public ResponseEntity<Void> checkPassword(@ModelAttribute @Valid MemberDto.PasswordCheckRequest dto, BindingResult br) {
    service.checkPassword(dto);
    return ResponseEntity.ok().build();
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/api/members/member")
  public ResponseEntity<Map<String, String>> readme(Principal principal) {
    return ResponseEntity.ok(service.read(principal.getName()));
  }

  @PreAuthorize("isAuthenticated()")
  @PatchMapping("/api/members/change-password")
  public ResponseEntity<Void> chenagePassword(@ModelAttribute @Valid MemberDto.PasswordCheckRequest dto, BindingResult br, Principal principal) {
    service.chnagePassword(dto, principal.getName());
    return ResponseEntity.ok().build();
  }

  @PreAuthorize("isAuthenticated()")
  @PatchMapping("/api/members/resign")
  public ResponseEntity<Void> resign(Principal principal, HttpSession session) {
    serivce.resign(principal.getName());
    session.invalidate();
    return ResponseEntity.ok().build();
  }
}
