package com.ikubinfo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ikubinfo.entities.BaseEntity;

public abstract class BaseRepository<ENTITY extends BaseEntity> {

	@PersistenceContext
	EntityManager entityManager;

	private Class<ENTITY> entityClass;

	public BaseRepository(Class<ENTITY> entityClass) {
		this.entityClass = entityClass;
	}

	public List<ENTITY> getAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ENTITY> query = builder.createQuery(entityClass);
		Root<ENTITY> root = query.from(entityClass);

		query.where(new Predicate[] {});
		query.select(root).distinct(true);

		List<ENTITY> entities = entityManager.createQuery(query).getResultList();
		return entities;
	}

	public ENTITY save(ENTITY entityToBePersisted) {
		entityManager.persist(entityToBePersisted);
		return entityToBePersisted;
	}

	public void delete(ENTITY entityToBeDeleted) {
		entityManager.remove(entityToBeDeleted);
	}

	public ENTITY update(ENTITY entityToBeUpdated) {
		entityManager.merge(entityToBeUpdated);
		return entityToBeUpdated;

	}

	public ENTITY findById(int id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ENTITY> query = builder.createQuery(entityClass);
		Root<ENTITY> root = query.from(entityClass);
		List<Predicate> predicates = new ArrayList<>();
		Predicate idPredicate = builder.equal(root.get("id"), id);
		predicates.add(idPredicate);

		query.where(predicates.toArray(new Predicate[] {}));
		query.select(root).distinct(true);
		try {
			return entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public Class<ENTITY> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<ENTITY> entityClass) {
		this.entityClass = entityClass;
	}

}
