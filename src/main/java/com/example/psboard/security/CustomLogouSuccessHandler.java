package com.example.psboard.security;

import java.io.*;
import java.util.*;

import com.example.psboard.util.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.stereotype.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

@Component
public class CustomLogouSuccessHandler implements LogoutSuccessHandler  {
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		Map<String, String> responseBody = Map.of("message", "로그아웃 성공");
		ResponseUtil.sendJsonResponse(response, HttpServletResponse.SC_OK, responseBody);
	}
}
