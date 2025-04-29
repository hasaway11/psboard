package com.example.psboard;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI customOpenAPI() {
    // http://localhost:8080/swagger-ui/index.html
    return new OpenAPI().components(new Components()).info(info());
  }

  private Info info() {
    return new Info().title("게시판 API").description("게시판 API 레퍼런스 문서화").version("1.0");
  }
}
