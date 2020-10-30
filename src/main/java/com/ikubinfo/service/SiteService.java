package com.ikubinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.converter.SiteConverter;
import com.ikubinfo.dto.SiteDto;
import com.ikubinfo.entities.SiteEntity;
import com.ikubinfo.repository.SiteRepository;

@Service
@Transactional
public class SiteService {

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private SiteConverter siteConverter;

	public List<SiteDto> getAllSites() {
		return siteConverter.toDtos(siteRepository.getAll());
	}

	public SiteDto addSite(SiteDto siteToBeAdded) {
		SiteEntity site = siteConverter.toEntity(siteToBeAdded);
		return siteConverter.toDto(siteRepository.save(site));
	}

	public SiteDto findSiteById(Integer id) {
		SiteEntity site = siteRepository.findById(id);
		return siteConverter.toDto(site);
	}

	public void deleteSite(Integer id) {
		SiteEntity site = siteRepository.findById(id);
		if (site != null) {
			siteRepository.delete(site);
		} else {
			System.out.println("Site doesn't exist"); // will be replaced with exception
		}

	}

	public SiteDto updateSite(Integer id, SiteDto siteToBeUpdated) {
		SiteEntity site = siteRepository.findById(id);
		if (site != null) {
			return siteConverter.toDto(siteRepository.update(siteConverter.toUpdateEntity(site, siteToBeUpdated)));
		} else {
			return null;
		}
	}
}
