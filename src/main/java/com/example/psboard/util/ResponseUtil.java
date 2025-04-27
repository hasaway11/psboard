package com.example.psboard.util;

import com.fasterxml.jackson.databind.*;
import jakarta.servlet.http.*;

import java.io.*;

public class ResponseUtil {
  private static final ObjectMapper mapper = new ObjectMapper();

  public static void sendJsonResponse(HttpServletResponse response, int statusCode, Object body) throws IOException {
    response.setStatus(statusCode);
    response.setContentType("application/json; charset=UTF-8");
    response.getWriter().write(mapper.writeValueAsString(body));
  }
}
