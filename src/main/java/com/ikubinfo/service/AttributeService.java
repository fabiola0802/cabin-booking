package com.ikubinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.converter.AttributeConverter;
import com.ikubinfo.dto.AttributeDto;
import com.ikubinfo.entities.AttributeEntity;
import com.ikubinfo.enums.AttributeType;
import com.ikubinfo.repository.AttributeRepository;

@Service
@Transactional
public class AttributeService {

	@Autowired
	private AttributeRepository attributeRepository;

	@Autowired
	private AttributeConverter attributeConverter;

	public AttributeDto createAttribute(AttributeDto attributeToBeCreated) {
		if (AttributeType.contains(attributeToBeCreated.getType())) {
			AttributeEntity attribute = attributeConverter.toEntity(attributeToBeCreated);
			return attributeConverter.toDto(attributeRepository.save(attribute));
		} else {
			return null; // replace with exception
		}

	}

	public void deleteAttribute(Integer id) {
		AttributeEntity attribute = attributeRepository.findById(id);
		if (attribute != null) {
			attributeRepository.delete(attribute);
		} else {
			System.out.println("Couldn't find attribute");// replace with exception
		}
	}

	public AttributeDto updateAttribute(Integer id, AttributeDto attributeToBeUpdated) {
		if (AttributeType.contains(attributeToBeUpdated.getType())) {
			AttributeEntity attribute = attributeRepository.findById(id);
			if (attribute != null) {
				return attributeConverter.toDto(attributeConverter.toUpdateEntity(attribute, attributeToBeUpdated));
			} else {
				// replace with exception
				return null;
			}
		} else {
			return null; // new exception
		}

	}

	public AttributeDto getAttributeById(Integer id) {
		return attributeConverter.toDto(attributeRepository.findById(id));
	}

	public List<AttributeDto> filterAttributesByType(AttributeType type) {

		if (type == null || AttributeType.contains(type)) {
			return attributeConverter.toDtos(attributeRepository.filterByType(type));
		} else {
			return null; // exception
		}
	}
}
