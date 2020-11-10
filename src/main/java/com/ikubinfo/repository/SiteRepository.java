package com.ikubinfo.repository;

import org.springframework.stereotype.Repository;

import com.ikubinfo.entities.SiteEntity;

@Repository
public class SiteRepository extends BaseRepository<SiteEntity> {

	public SiteRepository() {
		super(SiteEntity.class);
	}

}
