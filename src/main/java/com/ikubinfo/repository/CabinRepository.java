package com.ikubinfo.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

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
		query.where(builder.equal(root.get("cabinNumber"), cabinNumber),builder.equal(root.get("site"), siteId));
		return entityManager.createQuery(query).getSingleResult() != 0;
	}

	public boolean cabinNumberExists(Integer cabinNumber, Integer siteId, Integer id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<CabinEntity> root = query.from(CabinEntity.class);
		query.select(builder.count(root.get("cabinNumber")));
		query.where(builder.equal(root.get("cabinNumber"), cabinNumber), builder.equal(root.get("site"), siteId) ,builder.notEqual(root.get("id"), id));
		return entityManager.createQuery(query).getSingleResult() != 0;
	}
}
