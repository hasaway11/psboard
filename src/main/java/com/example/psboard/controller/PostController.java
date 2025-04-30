package com.example.psboard.controller;

import com.example.psboard.dto.*;
import com.example.psboard.entity.*;
import com.example.psboard.service.*;
import io.swagger.v3.oas.annotations.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.access.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;
import java.util.*;

@Validated
@RestController
@RequiredArgsConstructor
public class PostController {
  private final PostService service;
  private final int PAGE_SIZE = 10;

  @Operation(summary="글 목록", description="글 목록 출력")
  @GetMapping(path="/api/posts")
  public ResponseEntity<PostDto.Pages> findAll(@RequestParam int pageno) {
    if(pageno<=1)
      pageno = 1;
    return ResponseEntity.ok(service.findAll(pageno, PAGE_SIZE));
  }

  @Operation(summary="글  (*)", description="글 작성 하기")
  @Secured("ROLE_USER")
  @PostMapping(path="/api/posts")
  public ResponseEntity<Post> write(@Valid PostDto.Create dto, BindingResult br, Principal principal) {
    return ResponseEntity.ok(service.write(dto, principal.getName()));
  }
  
  @Operation(summary="글 읽기", description="글 읽기")
  @GetMapping(path="/api/posts/post")
  public ResponseEntity<Map<String,Object>> read(@RequestParam int pno, Principal principal) {
    String loginId = principal!=null? principal.getName() : null;
    return ResponseEntity.ok(service.read(pno, loginId));
  }

  @Operation(summary="글 변경 (*)", description="글 변경 하기")
  @PreAuthorize("isAuthenticated")
  @PutMapping("/api/posts/{pno}")
  public ResponseEntity<Void> update(@Valid PostDto.Update dto, BindingResult br, Principal principal) {
    service.update(dto, principal.getName());
    return ResponseEntity.ok().build();
  }

  @Operation(summary="글 삭제 (*)", description="글 삭제하기")
  @PreAuthorize("isAuthenticated")
  @DeleteMapping("/api/posts/{pno}")
  public ResponseEntity<Void> delete(@PathVariable int pno, Principal principal) {
    service.delete(pno, principal.getName());
    return ResponseEntity.ok().build();
  }

  @Operation(summary="좋아요 (*)", description="글 추천하기")
  @PreAuthorize("isAuthenticated")
  @PutMapping("/api/posts/good/{pno}")
  public ResponseEntity<Integer> good(@PathVariable int pno, Principal principal) {
    int goodCnt = service.good(pno, principal.getName());
    return ResponseEntity.ok(goodCnt);
  }

  @Operation(summary="싫어요 (*)", description="글 비추하기")
  @PreAuthorize("isAuthenticated")
  @PutMapping("/api/posts/bad/{pno}")
  public ResponseEntity<Integer> bad(@PathVariable int pno, Principal principal) {
    int badCnt = service.bad(pno, principal.getName());
    return ResponseEntity.ok(badCnt);
  }
}
