package com.ikubinfo.dto;

import java.time.LocalDate;

public class BookingDto extends BaseDto{

	private static final long serialVersionUID = 1L;

	private LocalDate bookingDate;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int numberOfPeople;
	private UserDto user;
	private CabinDto cabin;

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CabinDto getCabin() {
		return cabin;
	}

	public void setCabin(CabinDto cabin) {
		this.cabin = cabin;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

}
