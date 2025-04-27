package com.example.psboard.security;

import java.io.*;
import java.util.*;

import com.example.psboard.util.*;
import org.springframework.security.access.*;
import org.springframework.security.web.access.*;
import org.springframework.stereotype.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		Map<String, String> responseBody = Map.of("message", "작업 권한이 없습니다");
		ResponseUtil.sendJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, responseBody);
	}
}
