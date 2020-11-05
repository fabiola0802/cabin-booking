package com.ikubinfo.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikubinfo.dto.BookingDto;
import com.ikubinfo.entities.BookingEntity;

@Component
public class BookingConverter implements BaseConverter<BookingEntity, BookingDto> {

	@Autowired
	private UserConverter userConverter;
	@Autowired
	private CabinConverter cabinConverter;

	@Override
	public BookingEntity toEntity(BookingDto dto) {
		BookingEntity booking = new BookingEntity();
		booking.setBookingDate(dto.getBookingDate());
		booking.setCheckInDate(dto.getCheckInDate());
		booking.setCheckOutDate(dto.getCheckOutDate());
		booking.setNumberOfPeople(dto.getNumberOfPeople());
		booking.setUser(userConverter.toEntity(dto.getUser()));
		booking.setCabin(cabinConverter.toEntity(dto.getCabin()));
		return booking;
	}


	@Override
	public BookingDto toDto(BookingEntity entity) {
		BookingDto bookingDto = new BookingDto();
		bookingDto.setId(entity.getId());
		bookingDto.setBookingDate(entity.getBookingDate());
		bookingDto.setCheckInDate(entity.getCheckInDate());
		bookingDto.setCheckOutDate(entity.getCheckOutDate());
		bookingDto.setNumberOfPeople(entity.getNumberOfPeople());
		bookingDto.setUser(userConverter.toDto(entity.getUser()));
		bookingDto.setCabin(cabinConverter.toDto(entity.getCabin()));
		return bookingDto;
	}

}
