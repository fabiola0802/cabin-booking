package com.ikub.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikubinfo.dto.CabinDto;
import com.ikubinfo.entities.CabinEntity;

@Component
public class CabinConverter implements BaseConverter<CabinEntity, CabinDto> {

	@Autowired
	private SiteConverter siteConverter;

	@Override
	public CabinEntity toEntity(CabinDto dto) {
		CabinEntity cabin = new CabinEntity();
		cabin.setCabinNumber(dto.getCabinNumber());
		cabin.setNumberOfFloors(dto.getNumberOfFloors());
		cabin.setNumberOfKitchens(dto.getNumberOfKitchens());
		cabin.setNumberOfBedrooms(dto.getNumberOfBedrooms());
		cabin.setNumberOfBathrooms(dto.getNumberOfBathrooms());
		cabin.setMaxCapacity(dto.getMaxCapacity());
		cabin.setSite(siteConverter.toEntity(dto.getSite()));
		return cabin;
	}

	@Override
	public CabinDto toDto(CabinEntity entity) {
		CabinDto cabinDto = new CabinDto();
		cabinDto.setCabinNumber(entity.getCabinNumber());
		cabinDto.setId(entity.getId());
		cabinDto.setMaxCapacity(entity.getMaxCapacity());
		cabinDto.setNumberOfFloors(entity.getNumberOfFloors());
		cabinDto.setNumberOfKitchens(entity.getNumberOfKitchens());
		cabinDto.setNumberOfBathrooms(entity.getNumberOfBathrooms());
		cabinDto.setNumberOfBedrooms(entity.getNumberOfBedrooms());
		cabinDto.setSite(siteConverter.toDto(entity.getSite()));
		return cabinDto;
	}

}
