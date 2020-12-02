package com.ikubinfo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import com.ikubinfo.dto.CabinFilter;
import com.ikubinfo.entities.BookingEntity;
import com.ikubinfo.entities.CabinEntity;

@Repository
public class CabinRepository extends BaseRepository<CabinEntity> {

	public CabinRepository() {
		super(CabinEntity.class);

	}

	public boolean cabinNumberExists(Integer cabinNumber, Integer siteId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<CabinEntity> root = query.from(CabinEntity.class);
		query.select(builder.count(root.get("cabinNumber")));
		query.where(builder.equal(root.get("cabinNumber"), cabinNumber), builder.equal(root.get("site"), siteId));
		return entityManager.createQuery(query).getSingleResult() != 0;
	}

	public boolean cabinNumberExists(Integer cabinNumber, Integer siteId, Integer id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<CabinEntity> root = query.from(CabinEntity.class);
		query.select(builder.count(root.get("cabinNumber")));
		query.where(builder.equal(root.get("cabinNumber"), cabinNumber), 
				builder.equal(root.get("site"), siteId),
				builder.notEqual(root.get("id"), id));
		return entityManager.createQuery(query).getSingleResult() != 0;
	}

	public List<CabinEntity> getCabins(CabinFilter cabinFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CabinEntity> query = builder.createQuery(CabinEntity.class);
		Root<CabinEntity> root = query.from(CabinEntity.class);
		List<Predicate> predicates = new ArrayList<>();
		if (cabinFilter.getNumberOfFloors() != null) {
			predicates.add(builder.equal(root.get("numberOfFloors"), cabinFilter.getNumberOfFloors()));
		}
		if (cabinFilter.getNumberOfKitchens() != null) {
			predicates.add(builder.equal(root.get("numberOfKitchens"), cabinFilter.getNumberOfKitchens()));
		}
		if (cabinFilter.getNumberOfBathrooms() != null) {
			predicates.add(builder.equal(root.get("numberOfBathrooms"), cabinFilter.getNumberOfBathrooms()));
		}
		if (cabinFilter.getNumberOfBedrooms() != null) {
			predicates.add(builder.equal(root.get("numberOfBedrooms"), cabinFilter.getNumberOfBedrooms()));
		}
		if (cabinFilter.getMaxCapacity() != null) {
			predicates.add(builder.equal(root.get("maxCapacity"), cabinFilter.getMaxCapacity()));
		}
		if (cabinFilter.getPrice() != null) {
			predicates.add(builder.equal(root.get("price"), cabinFilter.getPrice()));
		}
		if (cabinFilter.getSiteId() != null) {
			predicates.add(builder.equal(root.get("site"), cabinFilter.getSiteId()));
		}
		if (cabinFilter.getAttributeIds() != null) {
			predicates.add(root.join("cabinAttributes").in(cabinFilter.getAttributeIds()));
		}
		
		if(cabinFilter.getFreeFrom() != null || cabinFilter.getFreeTo() != null) {
			//(select b from booking b where b.cabin_id = c.id and b.checkInDate >= :freeFrom and b.checkOutDate <= :freeTo)
			Subquery<BookingEntity> bookingSubquery = query.subquery(BookingEntity.class);
			Root<BookingEntity> bookingRoot = bookingSubquery.from(BookingEntity.class);
			bookingSubquery.select(bookingRoot);
			List<Predicate> subPredicates = new ArrayList<>();
			subPredicates.add(builder.equal(bookingRoot.get("cabin").get("id"), root.get("id")));
			
			if(cabinFilter.getFreeFrom() != null) {
				subPredicates.add(builder.greaterThanOrEqualTo(bookingRoot.get("checkInDate"), cabinFilter.getFreeFrom()));
			}

			if(cabinFilter.getFreeTo() != null) {
				subPredicates.add(builder.lessThanOrEqualTo(bookingRoot.get("checkOutDate"), cabinFilter.getFreeTo()));
			}
			
			bookingSubquery.where(subPredicates.toArray(new Predicate[] {}));
			
			predicates.add(builder.exists(bookingSubquery).not());
		}

		query.where(predicates.toArray(new Predicate[] {}));
		query.select(root).distinct(true);

		return entityManager.createQuery(query).getResultList();

	}
}
