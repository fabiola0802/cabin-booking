package com.ikubinfo.security.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ikubinfo.enums.Role;
import com.ikubinfo.security.LoginResponse;
import com.ikubinfo.security.jwt.JwtProvider;

@Component
public class AuthenticationService {

	@Autowired
	private JwtProvider jwtProvider;

	public LoginResponse createResponse(Authentication authentication) {
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateJwtToken(authentication);
		UserPrincipal userPrinciple = (UserPrincipal) authentication.getPrincipal();
		
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setId(userPrinciple.getId());
		loginResponse.setUsername(userPrinciple.getUsername());
		loginResponse.setToken(token);
		loginResponse.setEmail(userPrinciple.getEmail());
		loginResponse.setName(userPrinciple.getName());
		loginResponse.setSurname(userPrinciple.getSurname());
		loginResponse.setRole(Role.valueOf(userPrinciple.getAuthorities().stream()
				.map(authority -> authority.getAuthority()).collect(Collectors.toList()).get(0)));
		return loginResponse;
	}
}
