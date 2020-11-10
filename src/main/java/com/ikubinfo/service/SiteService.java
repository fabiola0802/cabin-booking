package com.ikubinfo.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.converter.SiteConverter;
import com.ikubinfo.dto.SiteDto;
import com.ikubinfo.entities.AttributeEntity;
import com.ikubinfo.entities.SiteEntity;
import com.ikubinfo.enums.AttributeType;
import com.ikubinfo.enums.BadRequestMessage;
import com.ikubinfo.enums.ExceptionMessage;
import com.ikubinfo.enums.ValidationMessage;
import com.ikubinfo.exceptions.BadRequestException;
import com.ikubinfo.exceptions.NotFoundException;
import com.ikubinfo.repository.AttributeRepository;
import com.ikubinfo.repository.SiteRepository;

@Service
@Transactional
public class SiteService {

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private SiteConverter siteConverter;

	@Autowired
	private AttributeRepository attributeRepository;

	public List<SiteDto> getAllSites() {
		return siteConverter.toDtos(siteRepository.getAll());
	}

	public SiteDto addSite(SiteDto siteToBeAdded) {
		if (siteToBeAdded.getId() != null) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID.getMessage());
		}
		List<AttributeEntity> attributeEntities = validateAttribute(siteToBeAdded);
		SiteEntity site = siteConverter.toEntity(siteToBeAdded);
		site.setSiteAttributes(attributeEntities);
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
		if (!site.getCabins().isEmpty()) {
			throw new BadRequestException(BadRequestMessage.CAN_NOT_DELETE_SITE.getMessage());
		} else {
			siteRepository.delete(site);
		}

	}

	public SiteDto updateSite(Integer id, SiteDto siteToBeUpdated) {
		if (id <= 0) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID.getMessage());
		}
		siteRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.SITE_NOT_FOUND.getMessage()));
		siteToBeUpdated.setId(id);
		List<AttributeEntity> attributeEntities = validateAttribute(siteToBeUpdated);
		SiteEntity site = siteConverter.toEntity(siteToBeUpdated);
		site.setSiteAttributes(attributeEntities);

		return siteConverter.toDto(siteRepository.update(site));

	}

	private List<AttributeEntity> validateAttribute(SiteDto site) {
		List<Integer> attributesIds = site.getAttributes().stream().map(attributeDto -> attributeDto.getId())
				.collect(Collectors.toList());
		List<AttributeEntity> attributeEntities = attributeRepository.findByIdsAndType(attributesIds,
				AttributeType.SITE);

		if (attributesIds.size() != attributeEntities.size()) {
			throw new BadRequestException(BadRequestMessage.INPUT_INVALID.getMessage());
		}
		return attributeEntities;
	}

}
