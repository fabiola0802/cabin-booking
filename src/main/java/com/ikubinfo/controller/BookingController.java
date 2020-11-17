package com.ikubinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikubinfo.dto.BookingDto;
import com.ikubinfo.service.BookingService;
import com.ikubinfo.utils.Routes;

@RestController
@RequestMapping(value = Routes.BOOKINGS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingController {
	
	@Autowired
	private BookingService bookingService;

	@PostMapping()
	public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto booking){
		return ResponseEntity.ok(bookingService.createBooking(6, booking));
	}
}
