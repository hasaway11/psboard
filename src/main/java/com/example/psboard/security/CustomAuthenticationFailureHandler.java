package com.example.psboard.security;

import java.io.*;
import java.util.*;

import com.example.psboard.dao.*;
import com.example.psboard.entity.*;
import com.example.psboard.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Autowired
	private MemberDao memberDao;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
		Map<String, String> responseBody =  Map.of("message", "알 수 없는 이유로 로그인에 실패했습니다.");
		if(e instanceof BadCredentialsException) {
			String username = request.getParameter("username");
			Member member = memberDao.findByUsername(username).get();
			if(member.getFailedAttempts()<4) {
				memberDao.increaseFailedAttempts(username);
				String message = "로그인에 " + (member.getFailedAttempts()+1) + "회 실패했습니다. 5회 실패 시 계정이 비활성화됩니다";
				responseBody =  Map.of("message", message);
			} else {
				memberDao.increaseFailedAttempts(username);
				memberDao.setLock(username);
				responseBody =  Map.of("message", "로그인에 5회 실패해 계정이 잠겼습니다");
			}
		}  else if (e instanceof LockedException) {
			responseBody =  Map.of("message", "잠긴 계정입니다.");
		}
		ResponseUtil.sendJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED, responseBody);
	}
}
