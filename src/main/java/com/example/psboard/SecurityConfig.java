package com.example.psboard;

import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;
import org.springframework.security.web.access.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.web.cors.*;

import java.util.*;

@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity(prePostEnabled=true, securedEnabled=true)
public class SecurityConfig  {
  private final AccessDeniedHandler accessDeniedHandler;
  private final AuthenticationSuccessHandler authenticationSuccessHandler;
  private final AuthenticationFailureHandler authenticationFailureHandler;
  private final LogoutSuccessHandler logoutSuccessHandler;
  private final AuthenticationEntryPoint authenticationEntryPoint;

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable());
    http.cors(config->config.configurationSource(corsConfigurationSource()));
    http.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/api/auth/login").successHandler(authenticationSuccessHandler)
        .failureHandler(authenticationFailureHandler));
    http.logout(logout -> logout.logoutUrl("/api/auth/logout").logoutSuccessHandler(logoutSuccessHandler));
    http.exceptionHandling(handler -> handler.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint));
    return http.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOriginPatterns(Arrays.asList("*"));
    config.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
    config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
    src.registerCorsConfiguration("/**", config);
    return src;
  }
}

