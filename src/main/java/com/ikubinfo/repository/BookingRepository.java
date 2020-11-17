package com.ikubinfo.repository;

import java.time.LocalDate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ikubinfo.entities.BookingEntity;

@Repository
public class BookingRepository extends BaseRepository<BookingEntity> {

	public BookingRepository() {
		super(BookingEntity.class);
	}

	public boolean cabinBooked(Integer id, LocalDate checkIn, LocalDate checkOut) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<BookingEntity> root = query.from(BookingEntity.class);

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

		Predicate finalPredicate = builder.or(insideAnExistingReservation, outsideAReservationButIncludingIt,
				checkOutInsideAnExistingReservation, checkInInsideAnExistingReservation);
		query.select(builder.count(root));
		query.where(finalPredicate, builder.equal(root.get("cabin"), id));

		return entityManager.createQuery(query).getSingleResult() != 0;
	}
}
