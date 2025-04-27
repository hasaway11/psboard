package com.example.psboard.controller;

import com.example.psboard.util.*;
import io.swagger.v3.oas.annotations.*;
import jakarta.annotation.*;
import org.springframework.http.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.*;

@RestController
public class ImageController {
  @PostConstruct
  public void init() {
    File uploadDir = new File(BoardConstant.PROFILE_FOLDER);
    if(!uploadDir.exists())
      uploadDir.mkdirs();
  }

  @Operation(summary="이미지 출력", description="이미지 출력하기")
  @GetMapping("/image/{filename}")
  public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
    File file = new File(BoardConstant.PROFILE_FOLDER, filename);
    if (!file.exists())
      return ResponseEntity.notFound().build();
    byte[] fileBytes = FileCopyUtils.copyToByteArray(file);
    // MIME 타입 설정 (예: image/jpeg)
    String mimeType = Files.probeContentType(file.toPath());
    return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).body(fileBytes);
  }
}
