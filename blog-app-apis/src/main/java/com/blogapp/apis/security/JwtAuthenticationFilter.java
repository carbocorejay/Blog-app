package com.blogapp.apis.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		
		// 1 :- get token
		
		String name = "Authorization";
		String requestToken= request.getHeader(name);
		
		// Bearer 2352523sdggd
		
		System.out.println(requestToken);
		
		String username = null ;
		
		String token = null ;
		
		if(requestToken!= null && requestToken.startsWith("Bearer")) {
			
			token = requestToken.substring(7);
		
	       try {
	           username = this.jwtTokenHelper.getUsernameFromToken(token);
	      } catch (IllegalArgumentException e) {
	    	  System.out.println("Unable to get Jwt token");
	      }catch (ExpiredJwtException e) {
			System.out.println("Jwt token has expired");
	   	} catch (MalformedJwtException e) {
			System.out.println("invalid jwt");
		}
			
		}else {
			System.out.println("JWT token does not begin with bearer");
		}
		
		
		// once we get the token then we validate it
		
		if(username!= null && SecurityContextHolder.getContext().getAuthentication()== null)
		{
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails))
			{
				//now setting authentication
				
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else {
				System.out.println("Invalid jwt token");
			}
		}
		else {
			System.out.println("username is null or context is not null");
		}
		
		
		
		filterChain.doFilter(request, response);
		
		
		
		
		
	}

}
