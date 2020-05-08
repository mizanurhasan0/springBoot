package com.spring.join;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



//@SpringBootApplication
//public class BookStore2Application {
//	public static void main(String[] args) {
//		SpringApplication.run(BookStore2Application.class, args);
//		
//		
//	}
//	
//
//}
@SpringBootApplication
public class BookStore2Application implements WebMvcConfigurer {

    private static Logger logger = LoggerFactory.getLogger(BookStore2Application.class.getName());

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Register resource handler for images
        registry.addResourceHandler("/images/**").addResourceLocations("/images/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
    }
	public static void main(String[] args) {
		SpringApplication.run(BookStore2Application.class, args);
		
		
	}
	

}