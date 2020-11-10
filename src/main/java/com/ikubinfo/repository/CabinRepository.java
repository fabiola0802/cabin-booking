package com.ikubinfo.repository;

import org.springframework.stereotype.Repository;

import com.ikubinfo.entities.CabinEntity;

@Repository
public class CabinRepository extends BaseRepository<CabinEntity> {

	public CabinRepository() {
		super(CabinEntity.class);

	}
}
