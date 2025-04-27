package com.example.psboard.security;

import java.io.*;
import java.util.*;

import com.example.psboard.util.*;
import org.springframework.security.core.*;
import org.springframework.security.web.*;
import org.springframework.stereotype.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		Map<String, String> responseBody = Map.of("message", "로그인이 필요합니다");
		ResponseUtil.sendJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED, responseBody);
	}
}
