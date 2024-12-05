package com.javaex.config;

import java.io.IOException;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF 보호 비활성화 (개발 단계에서만 권장, 실제 배포 시 활성화 필요)
            .csrf(csrf -> csrf.disable())
            // CORS 설정 활성화 (WebMvcConfig에서 이미 설정됨)
            .cors(Customizer.withDefaults())
            // 모든 요청을 허용하도록 설정
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()
            )
            // HTTP Basic 인증 비활성화
            .httpBasic(basic -> basic.disable());

        return http.build();
    }

    // 비밀번호 암호화용 PasswordEncoder 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 추가적인 헤더 필터 설정 (필요 시 활성화)
    @Bean
    public FilterRegistrationBean<Filter> headerFilter() {
        FilterRegistrationBean<Filter> filterRegBean = new FilterRegistrationBean<>();
        filterRegBean.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                    FilterChain filterChain) throws ServletException, IOException {
                // 필요 시 헤더 설정 추가
                // response.setHeader("Cross-Origin-Opener-Policy", "same-origin-allow-popups");
                // response.setHeader("Cross-Origin-Embedder-Policy", "require-corp");
                filterChain.doFilter(request, response);
            }
        });
        return filterRegBean;
    }
}
