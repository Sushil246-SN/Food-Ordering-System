package com.food.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	

	@Bean
	public PasswordEncoder passwordEncoder(){
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.csrf(csrf-> csrf.disable())
		.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		//public Apis
		.authorizeHttpRequests(auth->auth.requestMatchers("/api/auth/**","/api/health")
		.permitAll()
		//Customer and Admin
		.requestMatchers(HttpMethod.GET,"/api/restaurants","/api/restaurants/**","/api/foods","/api/foods/**")
		.hasAnyRole("CUSTOMER","ADMIN")
		//Admin access only
		.requestMatchers("/api/restaurants","/api/restaurants/**","/api/foods","/api/foods/**")
		.hasRole("ADMIN")
		//Customer and Admin
		.requestMatchers("/api/orders/**","/api/payments/**")
		.hasAnyRole("CUSTOMER","ADMIN")
		.requestMatchers("/api/orders/admin/**").hasRole("ADMIN")
		.requestMatchers("/api/orders/**").hasAnyRole("CUSTOMER","ADMIN")
		.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
}
