package com.ikubinfo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ikubinfo.entities.AttributeEntity;
import com.ikubinfo.enums.AttributeType;

@Repository
public class AttributeRepository extends BaseRepository<AttributeEntity> {

	public AttributeRepository() {
		super(AttributeEntity.class);

	}

	public List<AttributeEntity> filterByType(AttributeType attributeType) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AttributeEntity> query = builder.createQuery(AttributeEntity.class);
		Root<AttributeEntity> root = query.from(AttributeEntity.class);
		List<Predicate> predicates = new ArrayList<>();
		if (attributeType != null) {
			Predicate typePredicate = builder.equal(root.get("type"), attributeType);
			predicates.add(typePredicate);
		}

		query.where(predicates.toArray(new Predicate[] {}));
		query.select(root).distinct(true);

		return entityManager.createQuery(query).getResultList();

	}

	public List<AttributeEntity> findByIdsAndType(List<Integer> ids, AttributeType type) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AttributeEntity> query = builder.createQuery(AttributeEntity.class);
		Root<AttributeEntity> root = query.from(AttributeEntity.class);
		List<Predicate> predicates = new ArrayList<>();
		Predicate typePredicate = builder.equal(root.get("type"), type);
		predicates.add(typePredicate);
		Predicate idsPredicate = root.get("id").in(ids);
		predicates.add(idsPredicate);
		query.where(predicates.toArray(new Predicate[] {}));
		query.select(root).distinct(true);
		return entityManager.createQuery(query).getResultList();
	}

}

