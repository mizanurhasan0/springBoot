package com.blog.org.jwt.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry coreRegistry) {
		// TODO Auto-generated method stub
		coreRegistry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("*")
		.maxAge(3600L)
		.allowedHeaders("*")
		.exposedHeaders("Authorization")
		.allowCredentials(true);
	}

}
