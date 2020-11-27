package com.ikubinfo.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikubinfo.security.exception.InvalidTokenException;
import com.ikubinfo.utils.CustomErrorResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		CustomErrorResponse errorResponse = new CustomErrorResponse();
		HttpStatus status;
		List<String> errorMessage = new ArrayList<>();
		if (authException instanceof InvalidTokenException) {
			status = HttpStatus.UNAUTHORIZED;
			errorMessage.add(authException.getMessage());
			errorResponse.setMessage(Arrays.asList("InvalidTokenException"));
		} else {
			status = HttpStatus.FORBIDDEN;
			errorMessage.add(status.getReasonPhrase());
			errorResponse.setMessage(Arrays.asList(authException.getMessage()));
		}
		errorResponse.setStatus(status.value());
		response.setStatus(status.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		mapper.writeValue(response.getWriter(), errorResponse);
	}
}
