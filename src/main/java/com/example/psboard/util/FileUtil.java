package com.example.psboard.util;

import org.springframework.http.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

public class FileUtil {
  public static String convertToBase64(MultipartFile file) throws IOException {
    byte[] fileBytes = file.getBytes();
    return "data:" + file.getContentType() + ";base64," + Base64.getEncoder().encodeToString(fileBytes);
  }

  public static String getDefaultBase64() throws IOException {
    File file = new File(BoardConstant.PROFILE_FOLDER, BoardConstant.DEFAULT_PROFILE_NAME);
    FileInputStream fis = new FileInputStream(file);
    byte[] fileBytes = fis.readAllBytes();
    return "data:" + MediaType.IMAGE_JPEG_VALUE + ";base64," + Base64.getEncoder().encodeToString(fileBytes);
  }
}
