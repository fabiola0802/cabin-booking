package com.ikub.converter;

import org.springframework.stereotype.Component;

import com.ikubinfo.dto.SiteDto;
import com.ikubinfo.entities.SiteEntity;

@Component
public class SiteConverter implements BaseConverter<SiteEntity, SiteDto> {

	@Override
	public SiteEntity toEntity(SiteDto dto) {
		SiteEntity site = new SiteEntity();
		site.setCode(dto.getCode());
		site.setDescription(dto.getDescription());
		site.setLocation(dto.getLocation());
		return site;
	}

	@Override
	public SiteDto toDto(SiteEntity entity) {
		SiteDto siteDto = new SiteDto();
		siteDto.setId(entity.getId());
		siteDto.setCode(entity.getCode());
		siteDto.setDescription(entity.getDescription());
		siteDto.setLocation(entity.getLocation());
		return siteDto;
	}

}
