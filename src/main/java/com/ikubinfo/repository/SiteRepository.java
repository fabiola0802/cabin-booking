package com.ikubinfo.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ikubinfo.entities.SiteEntity;

@Repository
public class SiteRepository extends BaseRepository<SiteEntity> {

	public SiteRepository() {
		super(SiteEntity.class);
	}

	public boolean codeExists(Integer code) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<SiteEntity> root = query.from(SiteEntity.class);
		query.select(builder.count(root.get("code")));
		query.where(builder.equal(root.get("code"), code));
		return entityManager.createQuery(query).getSingleResult() != 0;

	}

	public boolean codeExists(Integer code, Integer id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<SiteEntity> root = query.from(SiteEntity.class);
		query.select(builder.count(root.get("code")));
		query.where(builder.equal(root.get("code"), code), builder.notEqual(root.get("id"), id));
		return entityManager.createQuery(query).getSingleResult() != 0;

	}
}
