package com.ikubinfo.converter;

import org.springframework.stereotype.Component;

import com.ikubinfo.dto.AttributeDto;
import com.ikubinfo.entities.AttributeEntity;

@Component
public class AttributeConverter implements BaseConverter<AttributeEntity, AttributeDto> {

	@Override
	public AttributeEntity toEntity(AttributeDto dto) {
		AttributeEntity attribute = new AttributeEntity();
		attribute.setId(dto.getId());
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

}
