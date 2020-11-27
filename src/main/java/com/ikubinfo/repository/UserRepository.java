package com.ikubinfo.repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ikubinfo.entities.UserEntity;

@Repository
public class UserRepository extends BaseRepository<UserEntity> {

	public UserRepository() {
		super(UserEntity.class);
	}

	public boolean usernameExists(String username) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<UserEntity> root = query.from(UserEntity.class);
		query.select(builder.count(root.get("username")));
		query.where(builder.equal(root.get("username"), username));
		return entityManager.createQuery(query).getSingleResult() != 0;
	}

	public boolean usernameExists(String username, Integer id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<UserEntity> root = query.from(UserEntity.class);
		query.select(builder.count(root.get("username")));
		query.where(builder.equal(root.get("username"), username), builder.notEqual(root.get("id"), id));
		return entityManager.createQuery(query).getSingleResult() != 0;
	}

	public UserEntity findByUsername(String username) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
		Root<UserEntity> root = query.from(UserEntity.class);
		query.select(root);
		query.where(builder.equal(root.get("username"), username));
		try {
			return entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
}
