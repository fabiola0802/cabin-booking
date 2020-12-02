package com.ikubinfo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.ikubinfo.entities.UserEntity;
import com.ikubinfo.exceptions.NotFoundException;
import com.ikubinfo.repository.UserRepository;
import com.ikubinfo.utils.messages.NotFoundExceptionMessage;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserEntity userEntity = userRepository.findByUsername(username);
		if (userEntity == null) {
			throw new NotFoundException(NotFoundExceptionMessage.USER_NOT_FOUND);
		}
		return UserPrincipal.build(userEntity);
	}
}
