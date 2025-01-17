package com.example.financeTracker;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry resgistry) {
		resgistry.addMapping("/api/**")
        .allowedOrigins("http://localhost:5173")  // Ensure this matches your frontend URL
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowedHeaders("*")  // Add headers you expect
        .allowCredentials(true);
	}
}
