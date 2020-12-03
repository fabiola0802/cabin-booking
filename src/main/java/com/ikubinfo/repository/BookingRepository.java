package com.ikubinfo.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ikubinfo.dto.BookingFilter;
import com.ikubinfo.entities.BookingEntity;

@Repository
public class BookingRepository extends BaseRepository<BookingEntity> {

	public BookingRepository() {
		super(BookingEntity.class);
	}
	
	Predicate getCabinBookedPredicate(Root<BookingEntity> root, CriteriaBuilder builder, LocalDate checkIn, LocalDate checkOut) {
		Predicate checkInLessThanOrEqualToCheckInDateDb = builder.greaterThanOrEqualTo(root.get("checkInDate"),
				checkIn);
		Predicate checkInGreaterThanOrEqualToCheckInDateDb = builder.lessThanOrEqualTo(root.get("checkInDate"),
				checkIn);
		Predicate checkOutGreaterThanCheckInDateDb = builder.lessThan(root.get("checkInDate"), checkOut);
		Predicate checkOutLessThanOrEqualToCheckOutDateDb = builder.greaterThanOrEqualTo(root.get("checkOutDate"),
				checkOut);
		Predicate checkOutGreaterThanOrEqualToCheckOutDateDb = builder.lessThanOrEqualTo(root.get("checkOutDate"),
				checkOut);
		Predicate checkOutLessThanCheckInDateDb = builder.greaterThan(root.get("checkOutDate"), checkIn);

		Predicate insideAnExistingReservation = builder.and(checkInGreaterThanOrEqualToCheckInDateDb,
				checkOutLessThanOrEqualToCheckOutDateDb);
		Predicate outsideAReservationButIncludingIt = builder.and(checkInLessThanOrEqualToCheckInDateDb,
				checkOutGreaterThanOrEqualToCheckOutDateDb);
		Predicate checkOutInsideAnExistingReservation = builder.and(checkInLessThanOrEqualToCheckInDateDb,
				checkOutGreaterThanCheckInDateDb, checkOutLessThanOrEqualToCheckOutDateDb);
		Predicate checkInInsideAnExistingReservation = builder.and(checkOutGreaterThanOrEqualToCheckOutDateDb,
				checkOutLessThanCheckInDateDb, checkInGreaterThanOrEqualToCheckInDateDb);
		
		
		return builder.or(insideAnExistingReservation, outsideAReservationButIncludingIt,
				checkOutInsideAnExistingReservation, checkInInsideAnExistingReservation);
	}

	public boolean cabinBooked(Integer id, LocalDate checkIn, LocalDate checkOut) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<BookingEntity> root = query.from(BookingEntity.class);

		Predicate cabinBookedPredicate = getCabinBookedPredicate(root, builder, checkIn, checkOut);
		query.select(builder.count(root));
		query.where(cabinBookedPredicate, builder.equal(root.get("cabin"), id));

		return entityManager.createQuery(query).getSingleResult() != 0;
	}

	public boolean cabinBooked(Integer id, Integer bookingId, LocalDate checkIn, LocalDate checkOut) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<BookingEntity> root = query.from(BookingEntity.class);

		Predicate cabinBookedPredicate = getCabinBookedPredicate(root, builder, checkIn, checkOut);
		query.select(builder.count(root));
		query.where(cabinBookedPredicate, builder.equal(root.get("cabin"), id), 
				builder.notEqual(root.get("id"), bookingId));

		return entityManager.createQuery(query).getSingleResult() != 0;
	}

	public List<BookingEntity> getAllBookings(BookingFilter booking) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BookingEntity> query = builder.createQuery(BookingEntity.class);
		Root<BookingEntity> root = query.from(BookingEntity.class);
		List<Predicate> predicates = new ArrayList<>();
		if(booking.getCabinId() != null) {
			predicates.add(builder.equal(root.get("cabin"), booking.getCabinId()));
		}
		if (booking.getBookingDate() != null) {
			predicates.add(builder.equal(root.get("bookingDate"), booking.getBookingDate()));
		}
		if (booking.getCheckInDate() != null) {
			if(booking.isCheckInStartingFrom()) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("checkInDate"), booking.getCheckInDate()));
			} else {
				predicates.add(builder.equal(root.get("checkInDate"), booking.getCheckInDate()));
			}
		}
		if (booking.getCheckOutDate() != null) {
			if(booking.isCheckOutUntil()) {
				predicates.add(builder.lessThanOrEqualTo(root.get("checkOutDate"), booking.getCheckOutDate()));
			} else {
				predicates.add(builder.equal(root.get("checkOutDate"), booking.getCheckOutDate()));
			}
		}
		if (booking.getNumberOfPeople() != null) {
			predicates.add(builder.equal(root.get("numberOfPeople"), booking.getNumberOfPeople()));
		}
		if (booking.getUserId() != null) {
			predicates.add(builder.equal(root.get("user"), booking.getUserId()));
		}
		query.where(predicates.toArray(new Predicate[] {}));
		query.select(root).distinct(true);
		return entityManager.createQuery(query)
//				.setFirstResult(0) //0*pageSize
//				.setMaxResults(10) //pageSize
				.getResultList();
	}
}
