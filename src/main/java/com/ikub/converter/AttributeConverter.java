package com.ikub.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ikubinfo.dto.AttributeDto;
import com.ikubinfo.entities.AttributeEntity;

@Component
public class AttributeConverter implements BaseConverter<AttributeEntity, AttributeDto> {

	@Override
	public AttributeEntity toEntity(AttributeDto dto) {
		AttributeEntity attribute = new AttributeEntity();
		attribute.setName(dto.getName());
		attribute.setType(dto.getType());
		return attribute;
	}

	@Override
	public AttributeDto toDto(AttributeEntity entity) {
		AttributeDto attributeDto = new AttributeDto();
		attributeDto.setId(entity.getId());
		attributeDto.setName(entity.getName());
		attributeDto.setType(entity.getType());
		return attributeDto;
	}

	public List<AttributeEntity> toEntities(List<AttributeDto> dtoAttributes) {
		List<AttributeEntity> attributes = new ArrayList<>();

		for (AttributeDto dtoAttribute : dtoAttributes) {
			attributes.add(toEntity(dtoAttribute));
		}
		return attributes;

	}

	public List<AttributeDto> toDtos(List<AttributeEntity> attributes) {
		List<AttributeDto> dtoAttributes = new ArrayList<>();

		for (AttributeEntity attribute : attributes) {
			dtoAttributes.add(toDto(attribute));
		}

		return dtoAttributes;
	}

}
