package com.ikubinfo.converter;

import org.springframework.stereotype.Component;

import com.ikubinfo.dto.UserDto;
import com.ikubinfo.dto.UserUpdateDto;
import com.ikubinfo.entities.UserEntity;
import com.ikubinfo.enums.Role;

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
		user.setRole(Role.CUSTOMER);
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

	public UserEntity toUpdateEntity(UserEntity entity, UserUpdateDto dto) {
		if (dto.getName() != null) {
			entity.setName(dto.getName());
		}
		if (dto.getSurname() != null) {
			entity.setSurname(dto.getSurname());
		}
		if (dto.getUsername() != null) {
			entity.setUsername(dto.getUsername());
		}
		if (dto.getEmail() != null) {
			entity.setEmail(dto.getEmail());
		}
		return entity;

	}
}
