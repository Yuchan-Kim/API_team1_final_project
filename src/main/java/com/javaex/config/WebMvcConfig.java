package com.javaex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
	  registry.addMapping("/api/**") // 경로
	          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	          .allowedOrigins("http://localhost:3000")
	        .allowedHeaders("*") // 모든 요청해더
	       .exposedHeaders("Authorization")//노출시킬헤더
	       .allowCredentials(true); // 쿠키허용
	  		
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**") //주소가 이걸로 시작하면
                .addResourceLocations("file:D:\\Final-TeamProject(KLSPH)\\upload\\"); //이렇게 시작하겠다 여기부터 뒤지겠다
    }
}