package com.ikubinfo.security.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikubinfo.enums.Role;
import com.ikubinfo.security.LoginRequest;
import com.ikubinfo.security.LoginResponse;
import com.ikubinfo.security.jwt.JwtProvider;
import com.ikubinfo.security.service.UserPrincipal;
import com.ikubinfo.utils.Routes;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = Routes.API, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@PostMapping(value = Routes.LOGIN)
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);
		return ResponseEntity.ok(createResponse(authentication, jwt));
	}

	private LoginResponse createResponse(Authentication authentication, String token) {
		UserPrincipal userPrinciple = (UserPrincipal) authentication.getPrincipal();
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setId(userPrinciple.getId());
		loginResponse.setUsername(userPrinciple.getUsername());
		loginResponse.setToken(token);
		loginResponse.setEmail(userPrinciple.getEmail());
		loginResponse.setName(userPrinciple.getName());
		loginResponse.setSurname(userPrinciple.getSurname());
		loginResponse.setRole(Role.valueOf(userPrinciple.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toList()).get(0)));
		return loginResponse;
	}

}
