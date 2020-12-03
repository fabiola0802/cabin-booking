package com.ikubinfo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ikubinfo.dto.BookingDto;
import com.ikubinfo.dto.BookingFilter;
import com.ikubinfo.service.BookingService;
import com.ikubinfo.utils.Routes;

@RestController
@RequestMapping(value = Routes.BOOKINGS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto booking) {
		return ResponseEntity.ok(bookingService.createBooking(booking));
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<BookingDto>> getAllBookings(@RequestParam(value = "cabinId", required = false) Integer cabinId,
			@RequestParam(value = "bookingDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingDate,
			@RequestParam(value = "checkInDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
			@RequestParam(value = "checkInStartingFrom", required = false) Boolean checkInStartingFrom,
			@RequestParam(value = "checkOutDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
			@RequestParam(value = "checkOutUntil", required = false) Boolean checkOutUntil,
			@RequestParam(value = "numberOfPeople", required = false) Integer numberOfPeople,
			@RequestParam(value = "userId", required = false) Integer userId) { //pageSize pageNumber
		BookingFilter bookingFilter = new BookingFilter(bookingDate, checkInDate, checkOutDate, numberOfPeople, userId);
		bookingFilter.setCabinId(cabinId);
		bookingFilter.setCheckInStartingFrom(checkInStartingFrom);
		bookingFilter.setCheckOutUntil(checkOutUntil);
		return ResponseEntity.ok(bookingService.getBookings(bookingFilter));
	}
	
	@GetMapping(value = "/my")
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<List<BookingDto>> getMyBookings(@RequestParam(value = "cabinId", required = false) Integer cabinId,
			@RequestParam(value = "bookingDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingDate,
			@RequestParam(value = "checkInDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
			@RequestParam(value = "checkInStartingFrom", required = false) Boolean checkInStartingFrom,
			@RequestParam(value = "checkOutDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
			@RequestParam(value = "checkOutUntil", required = false) Boolean checkOutUntil,
			@RequestParam(value = "numberOfPeople", required = false) Integer numberOfPeople) {
		BookingFilter bookingFilter = new BookingFilter(bookingDate, checkInDate, checkOutDate, numberOfPeople);
		bookingFilter.setCabinId(cabinId);
		bookingFilter.setCheckInStartingFrom(checkInStartingFrom);
		bookingFilter.setCheckOutUntil(checkOutUntil);
		return ResponseEntity.ok(bookingService.getCurrentUserBookings(bookingFilter));
	}

	@GetMapping(value = Routes.BY_ID)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<BookingDto> getBookingDetails(@PathVariable(value = Routes.ID) Integer id) {
		return ResponseEntity.ok(bookingService.getBooking(id));
	}

	@PutMapping(value = Routes.BY_ID)
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<BookingDto> updateBooking(@PathVariable(value = Routes.ID) Integer id,
			@Valid @RequestBody BookingDto booking) {
		return ResponseEntity.ok(bookingService.updateBooking(id, booking));
	}

	@DeleteMapping(value = Routes.BY_ID)
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<Void> deleteBooking(@PathVariable(value = Routes.ID) Integer id) {
		bookingService.deleteBooking(id);
		return ResponseEntity.noContent().build();
	}
}
