package com.example.psboard.security;

import java.io.*;
import java.util.*;

import com.example.psboard.util.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		Map<String, String> responseBody = Map.of("username", request.getParameter("username"));
		ResponseUtil.sendJsonResponse(response, HttpServletResponse.SC_OK, responseBody);
	}
}
