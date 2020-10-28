package com.ikub.converter;

import org.springframework.stereotype.Component;

import com.ikubinfo.dto.UserDto;
import com.ikubinfo.entities.UserEntity;

@Component
public class UserConverter implements BaseConverter<UserEntity, UserDto> {

	@Override
	public UserEntity toEntity(UserDto dto) {
		UserEntity user = new UserEntity();
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		return user;
	}

	@Override
	public UserDto toDto(UserEntity entity) {
		UserDto userDto = new UserDto();
		userDto.setId(entity.getId());
		userDto.setName(entity.getName());
		userDto.setSurname(entity.getSurname());
		userDto.setUsername(entity.getUsername());
		userDto.setPassword(entity.getPassword());
		return userDto;
	}

}
