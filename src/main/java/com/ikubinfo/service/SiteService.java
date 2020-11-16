package com.ikubinfo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ikubinfo.converter.SiteConverter;
import com.ikubinfo.dto.SiteDto;
import com.ikubinfo.entities.AttributeEntity;
import com.ikubinfo.entities.SiteEntity;
import com.ikubinfo.enums.AttributeType;
import com.ikubinfo.utils.messages.BadRequestMessage;
import com.ikubinfo.utils.messages.NotFoundExceptionMessage;
import com.ikubinfo.exceptions.BadRequestException;
import com.ikubinfo.exceptions.NotFoundException;
import com.ikubinfo.exceptions.ValidationException;
import com.ikubinfo.repository.AttributeRepository;
import com.ikubinfo.repository.SiteRepository;
import com.ikubinfo.utils.messages.ValidationMessage;

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
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID);
		}
		if (siteRepository.codeExists(siteToBeAdded.getCode())) {
			throw new ValidationException(ValidationMessage.SITE_CODE_EXISTS);
		}
		List<AttributeEntity> attributeEntities = validateAttribute(siteToBeAdded);
		SiteEntity site = siteConverter.toEntity(siteToBeAdded);
		site.setSiteAttributes(attributeEntities);
		return siteConverter.toDto(siteRepository.save(site));
	}

	public SiteDto findSiteById(Integer id) {
		SiteEntity site = siteRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.SITE_NOT_FOUND));
		return siteConverter.toDto(site);
	}

	public void deleteSite(Integer id) {
		SiteEntity site = siteRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.SITE_NOT_FOUND));
		if (!site.getCabins().isEmpty()) {
			throw new BadRequestException(BadRequestMessage.CAN_NOT_DELETE_SITE);
		} else {
			siteRepository.delete(site);
		}
	}

	public SiteDto updateSite(Integer id, SiteDto siteToBeUpdated) {
		if (id <= 0) {
			throw new ValidationException(ValidationMessage.ID_NOT_VALID);
		}
		SiteEntity site = siteRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.SITE_NOT_FOUND));
		siteToBeUpdated.setId(id);
		if (siteRepository.codeExists(siteToBeUpdated.getCode(), id)) {
			List<AttributeEntity> attributeEntities = validateAttribute(siteToBeUpdated);
			site = siteConverter.toEntity(siteToBeUpdated);
			site.setSiteAttributes(attributeEntities);
			return siteConverter.toDto(siteRepository.update(site));
		}
		throw new ValidationException(ValidationMessage.SITE_CODE_EXISTS);
	}

	private List<AttributeEntity> validateAttribute(SiteDto site) {
		List<Integer> attributesIds = site.getAttributes().stream().map(attributeDto -> attributeDto.getId())
				.collect(Collectors.toList());
		List<AttributeEntity> attributeEntities = attributeRepository.findByIdsAndType(attributesIds,
				AttributeType.SITE);
		if (attributesIds.size() != attributeEntities.size()) {
			throw new BadRequestException(BadRequestMessage.INPUT_INVALID);
		}
		return attributeEntities;
	}

}
