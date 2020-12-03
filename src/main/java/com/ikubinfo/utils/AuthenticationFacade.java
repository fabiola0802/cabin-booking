package com.ikubinfo.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ikubinfo.converter.UserConverter;
import com.ikubinfo.dto.UserDto;
import com.ikubinfo.security.service.UserPrincipal;

@Component
public class AuthenticationFacade {

	private final UserConverter userConverter;
	public AuthenticationFacade(UserConverter userConverter) {
		this.userConverter = userConverter;
	}
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public UserPrincipal getUserPrincipal() {
		return (UserPrincipal) getAuthentication().getPrincipal();
	}
	
	public UserDto getUserDto() {
		return userConverter.toDto(getUserPrincipal());
	}
}
