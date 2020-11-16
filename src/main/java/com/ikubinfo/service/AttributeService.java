package com.ikubinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.converter.AttributeConverter;
import com.ikubinfo.dto.AttributeDto;
import com.ikubinfo.entities.AttributeEntity;
import com.ikubinfo.entities.CabinEntity;
import com.ikubinfo.entities.SiteEntity;
import com.ikubinfo.enums.AttributeType;
import com.ikubinfo.utils.messages.NotFoundExceptionMessage;
import com.ikubinfo.exceptions.NotFoundException;
import com.ikubinfo.exceptions.ValidationException;
import com.ikubinfo.repository.AttributeRepository;
import com.ikubinfo.repository.CabinRepository;
import com.ikubinfo.repository.SiteRepository;
import com.ikubinfo.utils.messages.ValidationMessage;

@Service
@Transactional
public class AttributeService {

	@Autowired
	private AttributeRepository attributeRepository;

	@Autowired
	private AttributeConverter attributeConverter;

	@Autowired
	private CabinRepository cabinRepository;

	@Autowired
	private SiteRepository siteRepository;

	public AttributeDto createAttribute(AttributeDto attributeToBeCreated) {
		if (attributeToBeCreated.getId() != null) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID);
		}
		AttributeEntity attribute = attributeConverter.toEntity(attributeToBeCreated);
		return attributeConverter.toDto(attributeRepository.save(attribute));
	}

	public void deleteAttribute(Integer id) {
		AttributeEntity attribute = attributeRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.ATTRIBUTE_NOT_FOUND));
		if (attribute.getType().equals(AttributeType.CABIN)) {
			for (CabinEntity cabin : attribute.getCabins()) {
				cabin.getCabinAttributes().remove(attribute);
				cabinRepository.update(cabin);
			}
		} else {
			for (SiteEntity site : attribute.getSites()) {
				site.getSiteAttributes().remove(attribute);
				siteRepository.update(site);
			}
		}

		attributeRepository.delete(attribute);
	}

	public AttributeDto updateAttribute(Integer id, AttributeDto attributeToBeUpdated) {
		if (id <= 0) {
			throw new ValidationException(ValidationMessage.ID_NOT_VALID);
		}
		attributeRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.ATTRIBUTE_NOT_FOUND));
		attributeToBeUpdated.setId(id);
		return attributeConverter.toDto(attributeRepository.update(attributeConverter.toEntity(attributeToBeUpdated)));
	}

	public AttributeDto getAttributeById(Integer id) {
		return attributeConverter.toDto(attributeRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.ATTRIBUTE_NOT_FOUND)));
	}

	public List<AttributeDto> filterAttributesByType(String type) {
		if (type == null || AttributeType.contains(type)) {
			return attributeConverter.toDtos(attributeRepository.filterByType(AttributeType.findByName(type)));
		} else {
			throw new NotFoundException(NotFoundExceptionMessage.ATTRIBUTE_TYPE_NOT_FOUND);
		}
	}

}
