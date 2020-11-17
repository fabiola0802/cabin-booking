package com.ikubinfo.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikubinfo.dto.CabinDto;
import com.ikubinfo.entities.CabinEntity;

@Component
public class CabinConverter implements BaseConverter<CabinEntity, CabinDto> {

	@Autowired
	private SiteConverter siteConverter;

	@Autowired
	private AttributeConverter attributeConverter;

	@Override
	public CabinEntity toEntity(CabinDto dto) {
		CabinEntity cabin = new CabinEntity();
		cabin.setId(dto.getId());
		cabin.setCabinNumber(dto.getCabinNumber());
		cabin.setNumberOfFloors(dto.getNumberOfFloors());
		cabin.setNumberOfKitchens(dto.getNumberOfKitchens());
		cabin.setNumberOfBedrooms(dto.getNumberOfBedrooms());
		cabin.setNumberOfBathrooms(dto.getNumberOfBathrooms());
		cabin.setMaxCapacity(dto.getMaxCapacity());
		cabin.setPrice(dto.getPrice());
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
		cabinDto.setPrice(entity.getPrice());
		cabinDto.setSite(siteConverter.toDto(entity.getSite()));
		cabinDto.setAttributes(attributeConverter.toDtos(entity.getCabinAttributes()));
		return cabinDto;
	}

	public CabinDto toBaseDto(CabinEntity entity) {
		CabinDto cabinDto = new CabinDto();
		cabinDto.setCabinNumber(entity.getCabinNumber());
		cabinDto.setId(entity.getId());
		cabinDto.setMaxCapacity(entity.getMaxCapacity());
		cabinDto.setNumberOfFloors(entity.getNumberOfFloors());
		cabinDto.setNumberOfKitchens(entity.getNumberOfKitchens());
		cabinDto.setNumberOfBathrooms(entity.getNumberOfBathrooms());
		cabinDto.setNumberOfBedrooms(entity.getNumberOfBedrooms());
		cabinDto.setPrice(entity.getPrice());
		return cabinDto;
	}
}
