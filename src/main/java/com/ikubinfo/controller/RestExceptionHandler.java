package com.ikubinfo.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ikubinfo.exceptions.BadRequestException;
import com.ikubinfo.exceptions.NotFoundException;
import com.ikubinfo.exceptions.ValidationException;
import com.ikubinfo.utils.CustomErrorResponse;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { BadRequestException.class, ValidationException.class })
	protected ResponseEntity<CustomErrorResponse> handleBadRequestException(RuntimeException ex) {
		return generateResponse(ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { NotFoundException.class })
	protected ResponseEntity<CustomErrorResponse> handleNotFoundException(NotFoundException ex) {
		return generateResponse(ex, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { RuntimeException.class })
	protected ResponseEntity<CustomErrorResponse> handleGenericException(RuntimeException ex) {
		return generateResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<CustomErrorResponse> generateResponse(RuntimeException ex, HttpStatus status) {
		return generateResponse(ex.getMessage(), status);
	}

	private ResponseEntity<CustomErrorResponse> generateResponse(String errorMessage, HttpStatus status) {
		CustomErrorResponse response = new CustomErrorResponse();
		response.setStatus(status.value());
		response.setMessage(Arrays.asList(errorMessage));
		response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<CustomErrorResponse>(response, status);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorResponse response = new CustomErrorResponse();

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
//		String.join(",", errors);
		response.setStatus(status.value());
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(errors);
		return handleExceptionInternal(ex, response, headers, status, request);
	}
}
