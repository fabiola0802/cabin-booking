package com.ikubinfo.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.converter.UserConverter;
import com.ikubinfo.dto.PasswordDto;
import com.ikubinfo.dto.UserDto;
import com.ikubinfo.dto.UserUpdateDto;
import com.ikubinfo.entities.UserEntity;
import com.ikubinfo.enums.BadRequestMessage;
import com.ikubinfo.enums.ExceptionMessage;
import com.ikubinfo.enums.ValidationMessage;
import com.ikubinfo.exceptions.BadRequestException;
import com.ikubinfo.exceptions.NotFoundException;
import com.ikubinfo.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<UserDto> getAllUsers() {
		return userConverter.toDtos(userRepository.getAll());
	}

	public UserDto addUser(UserDto userToBeAdded) {
		if (userToBeAdded.getId() != null) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID.getMessage());
		} else {
			if (userRepository.usernameExists(userToBeAdded.getUsername())) {
				throw new ValidationException(ValidationMessage.USERNAME_EXISTS.getMessage());
			}
		}
		userToBeAdded.setPassword(passwordEncoder.encode(userToBeAdded.getPassword()));
		UserEntity user = userConverter.toEntity(userToBeAdded);
		return userConverter.toDto(userRepository.save(user));
	}

	public void deleteUser(Integer id) {
		UserEntity user = userRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

		userRepository.delete(user);
	}

	public UserDto getUserById(Integer id) {
		UserEntity user = userRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));
		return userConverter.toDto(user);
	}

	public UserDto updateUser(Integer id, UserUpdateDto userToBeUpdated) {
		if (id <= 0) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID.getMessage());
		}
		UserEntity user = userRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));
		userToBeUpdated.setId(id);
		if (userToBeUpdated.getUsername().equals(user.getUsername())
				|| !userRepository.usernameExists(userToBeUpdated.getUsername())) {
			return userConverter.toDto(userRepository.update(userConverter.toUpdateEntity(user, userToBeUpdated)));

		}
		throw new ValidationException(ValidationMessage.USERNAME_EXISTS.getMessage());

	}

	public void changePassword(Integer id, PasswordDto passwordDto) {
		UserEntity user = userRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

		if (!passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
			throw new BadRequestException(BadRequestMessage.PASSWORDS_DO_NOT_MATCH.getMessage());
		}
		if (passwordEncoder.matches(passwordDto.getNewPassword(), user.getPassword())) {
			throw new BadRequestException(BadRequestMessage.WRONG_PASSWORD.getMessage());
		}

		user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
		userRepository.update(user);

	}
}
