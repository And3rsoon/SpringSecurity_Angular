package com.exemplo.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exemplo.service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	 private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
				String headerAuth= request.getHeader(HttpHeaders.AUTHORIZATION);
				System.out.println(headerAuth);
				if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
					String token=headerAuth.substring(7);
					if (token != null && !jwtUtils.validate(token)) {
				        	String username = jwtUtils.getSubject(token);
				        	UserDetails userDetails = userDetailService.loadUserByUsername(username);
				        	UsernamePasswordAuthenticationToken authentication =new UsernamePasswordAuthenticationToken(
				        																	userDetails,
				        																	null,
				        																	userDetails.getAuthorities());
				       authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				       SecurityContextHolder.getContext().setAuthentication(authentication);
				      }
				}
		}
		catch (Exception e) {
				  logger.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(request, response);
					
	}
}
		       
		

	
			
		
		
	


