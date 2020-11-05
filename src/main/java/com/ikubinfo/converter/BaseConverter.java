package com.ikubinfo.converter;

import java.util.List;


import com.ikubinfo.dto.BaseDto;
import com.ikubinfo.entities.BaseEntity;
import static java.util.stream.Collectors.toList;

public interface BaseConverter<ENTITY extends BaseEntity, DTO extends BaseDto> {

	ENTITY toEntity(DTO dto);

	DTO toDto(ENTITY entity);

	public default List<ENTITY> toEntities(List<DTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(toList());
	}

	public default List<DTO> toDtos(List<ENTITY> entities) {
		return entities.stream().map(entity -> toDto(entity)).collect(toList());
	}

}
