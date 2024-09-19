package com.inha.springbootapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {

    private final Dotenv dotenv = Dotenv.configure()
                            .directory("/") // 디렉토리 지정
                            .filename(".env") // 파일명 지정
                            .load();

    private final String reactURL = "http://localhost:" + dotenv.get("REACT_HOST_PORT");

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS를 허용
                .allowedOrigins(reactURL) // React 애플리케이션의 URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true); // 자격 증명(쿠키 등)을 허용할지 여부
    }
}