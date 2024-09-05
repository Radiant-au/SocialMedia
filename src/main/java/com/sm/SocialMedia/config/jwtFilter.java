package com.sm.SocialMedia.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class jwtFilter extends OncePerRequestFilter {

	@Autowired
	CustomUserDetailsService userDetail;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		if(token != null && jwtGenerator.validateToken(token)) {
			String email = jwtGenerator.getEmailfromToken(token);
			if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				UserDetails userdetail = userDetail.loadUserByUsername(email);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userdetail, null , userdetail.getAuthorities());
				// Set details about the request (e.g., IP address, session ID)
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Store authentication object in SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(auth);

			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
