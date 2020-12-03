package com.ikubinfo.converter;

import org.springframework.stereotype.Component;

import com.ikubinfo.dto.UserDto;
import com.ikubinfo.dto.UserUpdateDto;
import com.ikubinfo.entities.UserEntity;
import com.ikubinfo.security.service.UserPrincipal;

@Component
public class UserConverter implements BaseConverter<UserEntity, UserDto> {

	@Override
	public UserEntity toEntity(UserDto dto) {
		UserEntity user = new UserEntity();
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setRole(dto.getRole());
		return user;
	}

	@Override
	public UserDto toDto(UserEntity entity) {
		UserDto userDto = new UserDto();
		userDto.setId(entity.getId());
		userDto.setName(entity.getName());
		userDto.setSurname(entity.getSurname());
		userDto.setUsername(entity.getUsername());
		userDto.setEmail(entity.getEmail());
		return userDto;
	}

	public UserEntity toEntity(UserUpdateDto dto) {
		UserEntity user = new UserEntity();
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		return user;

	}
	
	public UserDto toDto(UserPrincipal userPrincipal) {
		UserDto user = new UserDto();
		user.setId(userPrincipal.getId());
		user.setUsername(userPrincipal.getUsername());
		user.setEmail(userPrincipal.getEmail());
		user.setName(userPrincipal.getName());
		user.setSurname(userPrincipal.getSurname());
		return user;
	}
}
