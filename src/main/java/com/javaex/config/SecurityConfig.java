package com.javaex.config;

import java.io.IOException;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecurityConfig {
	// SecurityConfig나 별도의 설정 클래스에 추가
    @Bean
    public FilterRegistrationBean<Filter> headerFilter() {
        FilterRegistrationBean<Filter> filterRegBean = new FilterRegistrationBean<>();
        filterRegBean.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, 
                                          HttpServletResponse response, 
                                          FilterChain filterChain) throws ServletException, IOException {
                response.setHeader("Cross-Origin-Opener-Policy", "same-origin-allow-popups");
                response.setHeader("Cross-Origin-Embedder-Policy", "require-corp");
                filterChain.doFilter(request, response);
            }
        });
        return filterRegBean;
    }
}
