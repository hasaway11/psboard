package com.example.psboard.security;

import java.io.*;
import java.util.*;

import com.example.psboard.dao.*;
import com.example.psboard.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		Map<String, String> responseBody = Map.of("message", "로그인이 실패했습니다");
		ResponseUtil.sendJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED, responseBody);
	}
}
