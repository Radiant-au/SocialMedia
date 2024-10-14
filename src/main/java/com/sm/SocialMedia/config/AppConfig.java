package com.sm.SocialMedia.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, jwtFilter jwtFilter) throws Exception {

	    http.sessionManagement(session -> session
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless sessions for JWT
	        .authorizeHttpRequests(authorize -> authorize
	                .requestMatchers("/api/**").authenticated() // Protect /api/** routes
	                .anyRequest().permitAll()) // Permit all other requests
	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add your JWT filter before UsernamePasswordAuthenticationFilter
	        .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless authentication
	    	.cors(cors->cors.configurationSource(corsConfigurationSource()));
	    
	    return http.build(); // Build and return the SecurityFilterChain
	}
	
	private CorsConfigurationSource corsConfigurationSource() {
		return new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration cfg = new CorsConfiguration();
				cfg.setAllowedOrigins(Arrays.asList(
						"http://localhost:3000"
						));
				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList(
						"Authorization"
						));
				cfg.setMaxAge(3600L);
				
				return cfg;
			}
		};
	}

	@Bean
	AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception{
		return authConfig.getAuthenticationManager();
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
