package com.javaex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedOrigins("http://localhost:3000")
                .allowedHeaders("*")
                .exposedHeaders("Authorization", "Cross-Origin-Opener-Policy")  // COOP 헤더 추가
                .allowCredentials(true);
    }
    
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String saveDir;
		String osName = System.getProperty("os.name").toLowerCase();
		
		if(osName.contains("linux")) {
			System.out.println("리눅스");
			saveDir = "/home/ec2-user/upload/";
//			saveDir = "/app/upload/";
		}else {
			System.out.println("윈도우");
			saveDir = "D:\\team1_final_project\\upload\\";
		}
		
		registry.addResourceHandler("/upload/**")
				.addResourceLocations("file:" + saveDir);
	}
    
    
    
}
