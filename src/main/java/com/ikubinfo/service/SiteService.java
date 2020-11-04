package com.ikubinfo.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.converter.SiteConverter;
import com.ikubinfo.dto.SiteDto;
import com.ikubinfo.entities.SiteEntity;
import com.ikubinfo.enums.ExceptionMessage;
import com.ikubinfo.enums.ValidationMessage;
import com.ikubinfo.exceptions.NotFoundException;
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
		if (siteToBeAdded.getId() != null) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID.getMessage());
		}
		SiteEntity site = siteConverter.toEntity(siteToBeAdded);
		return siteConverter.toDto(siteRepository.save(site));
	}

	public SiteDto findSiteById(Integer id) {
		SiteEntity site = siteRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.SITE_NOT_FOUND.getMessage()));
		return siteConverter.toDto(site);
	}

	public void deleteSite(Integer id) {
		SiteEntity site = siteRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.SITE_NOT_FOUND.getMessage()));
		siteRepository.delete(site);
	}

	public SiteDto updateSite(Integer id, SiteDto siteToBeUpdated) {
		if (id <= 0) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID.getMessage());
		}
		siteRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.SITE_NOT_FOUND.getMessage()));
		siteToBeUpdated.setId(id);
		return siteConverter.toDto(siteRepository.update(siteConverter.toEntity(siteToBeUpdated)));

	}
}
