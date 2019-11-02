package com.tutorial.mysql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class AppSecurityBaseConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.headers().frameOptions().sameOrigin();
//        http.csrf().ignoringAntMatchers("/console/**");
//        http.authorizeRequests().antMatchers("/console/**").permitAll();
//		
//		http.httpBasic();
////		
		http
		.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
				.anyRequest().authenticated()
				.and().authorizeRequests().antMatchers(HttpMethod.OPTIONS,"h2-console/**").permitAll().and()
			// .formLogin().and()
			.httpBasic();
		http.headers().frameOptions().sameOrigin();
		
//		
		// this may not be required, depends on your app configuration
//        http.authorizeRequests()
//                // we need config just for console, nothing else             
//                .antMatchers("/h2_console/**").permitAll();
//        // this will ignore only h2-console csrf, spring security 4+
//        http.csrf().ignoringAntMatchers("/h2-console/**");
//        //this will allow frames with same origin which is much more safe
//        http.headers().frameOptions().sameOrigin();
	}

}
