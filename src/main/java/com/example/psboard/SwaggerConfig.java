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
    return new Info().title("Mongo Basic API").description("Mongo API reference for developers").version("1.0");
  }
}
