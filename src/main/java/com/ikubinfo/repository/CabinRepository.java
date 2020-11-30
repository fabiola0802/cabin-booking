package com.ikubinfo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ikubinfo.dto.CabinFilter;
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
		query.where(builder.equal(root.get("cabinNumber"), cabinNumber), builder.equal(root.get("site"), siteId),
				builder.notEqual(root.get("id"), id));
		return entityManager.createQuery(query).getSingleResult() != 0;
	}

	public List<CabinEntity> getCabins(CabinFilter cabin) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CabinEntity> query = builder.createQuery(CabinEntity.class);
		Root<CabinEntity> root = query.from(CabinEntity.class);
		List<Predicate> predicates = new ArrayList<>();
		if (cabin.getNumberOfFloors() != null) {
			predicates.add(builder.equal(root.get("numberOfFloors"), cabin.getNumberOfFloors()));
		}
		if (cabin.getNumberOfKitchens() != null) {
			predicates.add(builder.equal(root.get("numberOfKitchens"), cabin.getNumberOfKitchens()));
		}
		if (cabin.getNumberOfBathrooms() != null) {
			predicates.add(builder.equal(root.get("numberOfBathrooms"), cabin.getNumberOfBathrooms()));
		}
		if (cabin.getNumberOfBedrooms() != null) {
			predicates.add(builder.equal(root.get("numberOfBedrooms"), cabin.getNumberOfBedrooms()));
		}
		if (cabin.getMaxCapacity() != null) {
			predicates.add(builder.equal(root.get("maxCapacity"), cabin.getMaxCapacity()));
		}
		if (cabin.getPrice() != null) {
			predicates.add(builder.equal(root.get("price"), cabin.getPrice()));
		}
		if (cabin.getSiteId() != null) {
			predicates.add(builder.equal(root.get("site"), cabin.getSiteId()));
		}
		if (cabin.getAttributeIds() != null) {
			predicates.add(root.join("cabinAttributes").in(cabin.getAttributeIds()));
		}

		query.where(predicates.toArray(new Predicate[] {}));
		query.select(root).distinct(true);

		return entityManager.createQuery(query).getResultList();

	}
}
