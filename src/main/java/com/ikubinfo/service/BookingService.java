package com.ikubinfo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.converter.BookingConverter;
import com.ikubinfo.dto.BookingDto;
import com.ikubinfo.dto.BookingFilter;
import com.ikubinfo.entities.BookingEntity;
import com.ikubinfo.entities.CabinEntity;
import com.ikubinfo.exceptions.BadRequestException;
import com.ikubinfo.exceptions.NotFoundException;
import com.ikubinfo.exceptions.ValidationException;
import com.ikubinfo.repository.BookingRepository;
import com.ikubinfo.repository.CabinRepository;
import com.ikubinfo.utils.AuthenticationFacade;
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
	private AuthenticationFacade authenticationFacade;

	public BookingDto createBooking(BookingDto bookingDto) {
		if (bookingDto.getId() != null) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID);
		}
		bookingDto.setUser(authenticationFacade.getUserDto());

		CabinEntity cabin = cabinRepository.findOptionalById(bookingDto.getCabin().getId())
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.CABIN_NOT_FOUND));
		bookingDto.setBookingDate(LocalDate.now());
		validatingDates(bookingDto);
		if (bookingRepository.cabinBooked(bookingDto.getCabin().getId(), bookingDto.getCheckInDate(),
				bookingDto.getCheckOutDate())) {
			throw new BadRequestException(BadRequestMessage.CABIN_ALREADY_BOOKED);
		}

		BookingEntity booking = bookingConverter.toEntity(bookingDto);
		booking.setCabin(cabin);
		return bookingConverter.toDto(bookingRepository.save(booking));
	}

	public BookingDto getBooking(Integer bookingId) {
		return bookingConverter.toDto(bookingRepository.findOptionalById(bookingId)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.BOOKING_NOT_FOUND)));
	}

	public List<BookingDto> getCurrentUserBookings(BookingFilter bookingFilter) {
		bookingFilter.setUserId(authenticationFacade.getUserPrincipal().getId());
		return bookingConverter.toDtos(bookingRepository.getAllBookings(bookingFilter));
	}

	public List<BookingDto> getBookings(BookingFilter bookingFilter) {
		return bookingConverter.toDtos(bookingRepository.getAllBookings(bookingFilter));
	}

	public void deleteBooking(Integer id) {
		BookingEntity booking = bookingRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.BOOKING_NOT_FOUND));
		if (!authenticationFacade.getUserPrincipal().getId().equals(booking.getUser().getId())) {
			throw new BadRequestException(BadRequestMessage.WRONG_USER);
		}
		if (LocalDate.now().compareTo(booking.getCheckInDate().minusDays(3)) > 0) {
			throw new BadRequestException(BadRequestMessage.BOOKING_CAN_NOT_BE_CANCELLED);
		}
		bookingRepository.delete(booking);
	}

	public BookingDto updateBooking(Integer id, BookingDto bookingDto) {
		if (id <= 0) {
			throw new ValidationException(ValidationMessage.ID_NOT_VALID);
		}
		BookingEntity booking = bookingRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.BOOKING_NOT_FOUND));
		CabinEntity cabin = cabinRepository.findOptionalById(bookingDto.getCabin().getId())
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.CABIN_NOT_FOUND));
		bookingDto.setBookingDate(LocalDate.now());
		validatingDates(bookingDto);
		if (bookingRepository.cabinBooked(cabin.getId(), booking.getId(), bookingDto.getCheckInDate(),
				bookingDto.getCheckOutDate())) {
			throw new BadRequestException(BadRequestMessage.CABIN_ALREADY_BOOKED);
		}
		if (!authenticationFacade.getUserPrincipal().getId().equals(booking.getUser().getId())) {
			throw new BadRequestException(BadRequestMessage.WRONG_USER);
		}
		bookingDto.setId(id);
		bookingDto.setUser(authenticationFacade.getUserDto());
		BookingEntity bookingEntity = bookingConverter.toEntity(bookingDto);
		bookingEntity.setCabin(cabin);
		return bookingConverter.toDto(bookingRepository.update(bookingEntity));
	}

	private void validatingDates(BookingDto bookingDto) {
		if (bookingDto.getCheckInDate().isBefore(bookingDto.getBookingDate())
				|| bookingDto.getCheckInDate().isAfter(bookingDto.getCheckOutDate())) {
			throw new BadRequestException(BadRequestMessage.WRONG_DATES);
		}
	}
}
