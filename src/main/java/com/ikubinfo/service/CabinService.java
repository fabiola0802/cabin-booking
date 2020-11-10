package com.ikubinfo.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.converter.CabinConverter;
import com.ikubinfo.dto.CabinDto;
import com.ikubinfo.entities.AttributeEntity;
import com.ikubinfo.entities.CabinEntity;
import com.ikubinfo.entities.SiteEntity;
import com.ikubinfo.enums.AttributeType;
import com.ikubinfo.enums.BadRequestMessage;
import com.ikubinfo.enums.ExceptionMessage;
import com.ikubinfo.enums.ValidationMessage;
import com.ikubinfo.exceptions.BadRequestException;
import com.ikubinfo.exceptions.NotFoundException;
import com.ikubinfo.repository.AttributeRepository;
import com.ikubinfo.repository.CabinRepository;
import com.ikubinfo.repository.SiteRepository;

@Service
@Transactional
public class CabinService {

	@Autowired
	private CabinRepository cabinRepository;

	@Autowired
	private CabinConverter cabinConverter;

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private AttributeRepository attributeRepository;

	public CabinDto createCabin(CabinDto cabinToBeCreated) {
		if (cabinToBeCreated.getId() != null) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID.getMessage());
		}
		SiteEntity site = siteRepository.findOptionalById(cabinToBeCreated.getSite().getId())
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.SITE_NOT_FOUND.getMessage()));
		List<AttributeEntity> attributeEntities = validateAttributes(cabinToBeCreated);
		CabinEntity cabinEntity = cabinConverter.toEntity(cabinToBeCreated);
		cabinEntity.setSite(site);
		cabinEntity.setCabinAttributes(attributeEntities);
		return cabinConverter.toDto(cabinRepository.save(cabinEntity));
	}

	public CabinDto updateCabin(Integer id, CabinDto cabinToBeUpdated) {
		if (id <= 0) {
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID.getMessage());
		}
		cabinRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.CABIN_NOT_FOUND.getMessage()));
		cabinToBeUpdated.setId(id);
		SiteEntity site = siteRepository.findOptionalById(cabinToBeUpdated.getSite().getId())
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.SITE_NOT_FOUND.getMessage()));
		List<AttributeEntity> attributeEntities = validateAttributes(cabinToBeUpdated);
		CabinEntity cabinEntity = cabinConverter.toEntity(cabinToBeUpdated);
		cabinEntity.setSite(site);
		cabinEntity.setCabinAttributes(attributeEntities);
		return cabinConverter.toDto(cabinRepository.update(cabinEntity));
	}

	public List<CabinDto> getCabins() {
		return cabinConverter.toDtos(cabinRepository.getAll());
	}

	public CabinDto getCabinById(Integer id) {
		return cabinConverter.toDto(cabinRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.CABIN_NOT_FOUND.getMessage())));
	}

	public void deleteCabin(Integer id) {
		CabinEntity cabin = cabinRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessage.CABIN_NOT_FOUND.getMessage()));
		cabinRepository.delete(cabin);
	}

	private List<AttributeEntity> validateAttributes(CabinDto cabin) {
		List<Integer> cabinAttributeIds = cabin.getAttributes().stream().map(attributeDto -> attributeDto.getId())
				.collect(Collectors.toList());
		List<AttributeEntity> cabinAttributes = attributeRepository.findByIdsAndType(cabinAttributeIds,
				AttributeType.CABIN);
		if (cabin.getAttributes().size() != cabinAttributes.size()) {
			throw new BadRequestException(BadRequestMessage.INPUT_INVALID.getMessage());
		}
		return cabinAttributes;
	}

}
