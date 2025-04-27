package com.example.psboard.controller;

import java.security.*;
import java.util.*;

import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import org.springframework.web.servlet.*;

@RequiredArgsConstructor
@RestController
public class AuthController {
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	// 프론트엔드에서 현재 로그인 상태를 물어오면 응답
	// 로그인한 경우 : 200 + 로그인 아이디
	// 비로그인 : 409 + message
	@GetMapping(path="/api/auth/check")
	public ResponseEntity<Map<String,String>> checkLogin(Principal principal) {
		if(principal!=null)
			return ResponseEntity.ok(Map.of("username", principal.getName()));
		return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "로그인되지 않았습니다"));
	}
}
