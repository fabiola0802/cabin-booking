package com.ikubinfo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.converter.BookingConverter;
import com.ikubinfo.dto.BookingDto;
import com.ikubinfo.entities.BookingEntity;
import com.ikubinfo.entities.CabinEntity;
import com.ikubinfo.exceptions.BadRequestException;
import com.ikubinfo.exceptions.NotFoundException;
import com.ikubinfo.exceptions.ValidationException;
import com.ikubinfo.repository.BookingRepository;
import com.ikubinfo.repository.CabinRepository;
import com.ikubinfo.utils.messages.BadRequestMessage;
import com.ikubinfo.utils.messages.NotFoundExceptionMessage;
import com.ikubinfo.utils.messages.ValidationMessage;

@Service
@Transactional
public class BookingService {

	@Autowired
	private BookingConverter bookingConverter;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private CabinRepository cabinRepository;

	@Autowired
	private UserService userService;

	public BookingDto createBooking(Integer loggedUserId, BookingDto bookingDto) {
		if (bookingDto.getId() != null) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID);
		}
		bookingDto.setUser(userService.getCurrentUser(loggedUserId));
		bookingDto.setBookingDate(LocalDate.now());
		CabinEntity cabin = cabinRepository.findOptionalById(bookingDto.getCabin().getId())
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.CABIN_NOT_FOUND));
		if (bookingDto.getCheckInDate().isBefore(bookingDto.getBookingDate())
				|| bookingDto.getCheckInDate().isAfter(bookingDto.getCheckOutDate())) {
			throw new BadRequestException(BadRequestMessage.WRONG_DATES);
		}
		if (bookingRepository.cabinBooked(bookingDto.getCabin().getId(), bookingDto.getCheckInDate(),
				bookingDto.getCheckOutDate())) {
			throw new BadRequestException(BadRequestMessage.CABIN_ALREADY_BOOKED);
		}
		BookingEntity booking  = bookingConverter.toEntity(bookingDto);
		booking.setCabin(cabin);
		return bookingConverter.toDto(bookingRepository.save(booking));
	}
}
