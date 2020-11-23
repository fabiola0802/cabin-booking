package com.ikubinfo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikubinfo.dto.BookingDto;
import com.ikubinfo.security.service.UserPrinciple;
import com.ikubinfo.service.BookingService;
import com.ikubinfo.utils.AuthenticationFacade;
import com.ikubinfo.utils.Routes;

@RestController
@RequestMapping(value = Routes.BOOKINGS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired 
	private AuthenticationFacade authenticationFacade;

	@PostMapping()
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto booking) {
		UserPrinciple userPrinciple = (UserPrinciple) authenticationFacade.getAuthentication().getPrincipal();
		return ResponseEntity.ok(bookingService.createBooking(userPrinciple.getId(), booking));
	}

	@GetMapping(value = Routes.BY_ID)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<BookingDto>> getAllBookingsForCabin(@PathVariable(value = Routes.ID) Integer id) {
		return ResponseEntity.ok(bookingService.getAllBookingsOfACabin(id));
	}

	@PutMapping(value = Routes.BY_ID)
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<BookingDto> updateBooking(@PathVariable(value = Routes.ID) Integer id,
			@Valid @RequestBody BookingDto booking) {
		UserPrinciple userPrinciple = (UserPrinciple) authenticationFacade.getAuthentication().getPrincipal();
		return ResponseEntity.ok(bookingService.updateBooking(id, userPrinciple.getId(), booking));
	}

	@DeleteMapping(value = Routes.BY_ID)
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<Void> deleteBooking(@PathVariable(value = Routes.ID) Integer id) {
		UserPrinciple userPrinciple = (UserPrinciple) authenticationFacade.getAuthentication().getPrincipal();
		bookingService.deleteBooking(userPrinciple.getId(), id);
		return ResponseEntity.noContent().build();
	}
}
