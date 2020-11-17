package com.ikubinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ikubinfo.converter.UserConverter;
import com.ikubinfo.dto.PasswordDto;
import com.ikubinfo.dto.UserDto;
import com.ikubinfo.dto.UserUpdateDto;
import com.ikubinfo.entities.UserEntity;
import com.ikubinfo.utils.messages.BadRequestMessage;
import com.ikubinfo.utils.messages.NotFoundExceptionMessage;
import com.ikubinfo.enums.Role;
import com.ikubinfo.exceptions.BadRequestException;
import com.ikubinfo.exceptions.NotFoundException;
import com.ikubinfo.exceptions.ValidationException;
import com.ikubinfo.repository.UserRepository;
import com.ikubinfo.utils.messages.ValidationMessage;

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

	public UserDto registerUser(UserDto user) {
		user.setRole(Role.CUSTOMER);
		return addUser(user);
	}

	public UserDto addUser(UserDto userToBeAdded) {
		if (userToBeAdded.getId() != null) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID);
		} else if (userRepository.usernameExists(userToBeAdded.getUsername())) {
			throw new ValidationException(ValidationMessage.USERNAME_EXISTS);
		}
		userToBeAdded.setPassword(passwordEncoder.encode(userToBeAdded.getPassword()));
		UserEntity user = userConverter.toEntity(userToBeAdded);
		return userConverter.toDto(userRepository.save(user));
	}

	public void deleteUser(Integer id) {
		UserEntity user = userRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.USER_NOT_FOUND));

		userRepository.delete(user);
	}

	public UserDto getUserById(Integer id) {
		UserEntity user = userRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.USER_NOT_FOUND));
		return userConverter.toDto(user);
	}

	public UserDto updateUser(Integer id, UserUpdateDto userToBeUpdated) {
		if (id <= 0) {
			throw new ValidationException(ValidationMessage.ID_NOT_VALID);
		}
		UserEntity user = userRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.USER_NOT_FOUND));
		userToBeUpdated.setId(id);

		if (userRepository.usernameExists(userToBeUpdated.getUsername(), id)) {
			throw new ValidationException(ValidationMessage.USERNAME_EXISTS);
		}
		UserEntity userUpd = userConverter.toEntity(userToBeUpdated);
		userUpd.setPassword(user.getPassword());
		userUpd.setRole(user.getRole());
		return userConverter.toDto(userRepository.update(userConverter.toEntity(userToBeUpdated)));
	}

	public void changePassword(Integer id, PasswordDto passwordDto) {
		UserEntity user = userRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.USER_NOT_FOUND));

		if (!passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
			throw new BadRequestException(BadRequestMessage.PASSWORDS_DO_NOT_MATCH);
		}
		if (passwordEncoder.matches(passwordDto.getNewPassword(), user.getPassword())) {
			throw new BadRequestException(BadRequestMessage.WRONG_PASSWORD);
		}
		if (!passwordDto.getNewPassword().equals(passwordDto.getConfirmPassword())) {
			throw new BadRequestException(BadRequestMessage.WRONG_CONFIRM_PASSWORD);
		}
		user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
		userRepository.update(user);
	}
	
	public UserDto getCurrentUser(Integer id){
		UserEntity user = userRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.USER_NOT_FOUND));
        return userConverter.toDto(user);
	}
}
