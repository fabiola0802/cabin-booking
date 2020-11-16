package com.ikubinfo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikubinfo.dto.PasswordDto;
import com.ikubinfo.dto.UserDto;
import com.ikubinfo.dto.UserUpdateDto;
import com.ikubinfo.service.UserService;
import com.ikubinfo.utils.Routes;

@RestController
@RequestMapping(value = Routes.USERS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@PostMapping
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto user) {
		return ResponseEntity.ok(userService.addUser(user));
	}

	@PostMapping(value = Routes.REGISTER)
	public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto user) {
		return ResponseEntity.ok(userService.addUser(user));
	}

	@DeleteMapping(value = Routes.BY_ID)
	public ResponseEntity<Void> deleteUser(@PathVariable(value = Routes.ID) Integer id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = Routes.BY_ID)
	public ResponseEntity<UserDto> getUserById(@PathVariable(value = Routes.ID) Integer id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@PostMapping(value = Routes.BY_ID)
	public ResponseEntity<Void> changePassword(@PathVariable(value = Routes.ID) Integer id,
			@Valid @RequestBody PasswordDto passwordDto) {
		userService.changePassword(id, passwordDto);
		return ResponseEntity.ok().build();
	}

	@PutMapping(value = Routes.BY_ID)
	public ResponseEntity<UserDto> updateUser(@PathVariable(value = Routes.ID) Integer id,
			@Valid @RequestBody UserUpdateDto userToBeUpdated) {
		return ResponseEntity.ok(userService.updateUser(id, userToBeUpdated));
	}
}
