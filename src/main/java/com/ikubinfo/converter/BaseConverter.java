package com.ikubinfo.converter;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.dto.BaseDto;
import com.ikubinfo.entities.BaseEntity;

public interface BaseConverter<ENTITY extends BaseEntity, DTO extends BaseDto> {

	ENTITY toEntity(DTO dto);

	DTO toDto(ENTITY entity);
	
//	ENTITY toUpdateEntity(ENTITY entity, DTO dto);

	public default List<ENTITY> toEntities(List<DTO> dtos) {
		List<ENTITY> entities = new ArrayList<>();

		for (DTO dto : dtos) {
			entities.add(toEntity(dto));
		}
		return entities;

	}

	public default List<DTO> toDtos(List<ENTITY> entities) {
		List<DTO> dtos = new ArrayList<>();

		for (ENTITY entity : entities) {
			dtos.add(toDto(entity));
		}

		return dtos;
	}

}
