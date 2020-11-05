package com.ikubinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.converter.AttributeConverter;
import com.ikubinfo.dto.AttributeDto;
import com.ikubinfo.entities.AttributeEntity;
import com.ikubinfo.enums.AttributeType;
import com.ikubinfo.enums.ExceptionMessage;
import com.ikubinfo.enums.ValidationMessage;
import com.ikubinfo.exceptions.NotFoundException;
import com.ikubinfo.exceptions.ValidationException;
import com.ikubinfo.repository.AttributeRepository;

@Service
@Transactional
public class AttributeService {

	@Autowired
	private AttributeRepository attributeRepository;

	@Autowired
	private AttributeConverter attributeConverter;

	public AttributeDto createAttribute(AttributeDto attributeToBeCreated) {
		if (attributeToBeCreated.getId() != null) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID.getMessage());
		}
		AttributeEntity attribute = attributeConverter.toEntity(attributeToBeCreated);
		return attributeConverter.toDto(attributeRepository.save(attribute));
	}

	public void deleteAttribute(Integer id) {
		AttributeEntity attribute = attributeRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.ATTRIBUTE_NOT_FOUND.getMessage()));
		attributeRepository.delete(attribute);
	}

	public AttributeDto updateAttribute(Integer id, AttributeDto attributeToBeUpdated) {
		if (id <= 0) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID.getMessage());
		}
		attributeRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.ATTRIBUTE_NOT_FOUND.getMessage()));
		attributeToBeUpdated.setId(id);
		return attributeConverter.toDto(attributeRepository.update(attributeConverter.toEntity(attributeToBeUpdated)));
	}

	public AttributeDto getAttributeById(Integer id) {
		return attributeConverter.toDto(attributeRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.ATTRIBUTE_NOT_FOUND.getMessage())));
	}

	public List<AttributeDto> filterAttributesByType(String type) {
		if (type == null || AttributeType.contains(type)) {
			return attributeConverter.toDtos(attributeRepository.filterByType(AttributeType.findByName(type)));
		} else {
			throw new NotFoundException(ExceptionMessage.ATTRIBUTE_TYPE_NOT_FOUND.getMessage());
		}
	}

}
