package com.ikubinfo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.converter.CabinConverter;
import com.ikubinfo.dto.AttributeDto;
import com.ikubinfo.dto.CabinDto;
import com.ikubinfo.dto.CabinFilter;
import com.ikubinfo.entities.AttributeEntity;
import com.ikubinfo.entities.CabinEntity;
import com.ikubinfo.entities.SiteEntity;
import com.ikubinfo.enums.AttributeType;
import com.ikubinfo.exceptions.BadRequestException;
import com.ikubinfo.exceptions.NotFoundException;
import com.ikubinfo.exceptions.ValidationException;
import com.ikubinfo.repository.AttributeRepository;
import com.ikubinfo.repository.CabinRepository;
import com.ikubinfo.repository.SiteRepository;
import com.ikubinfo.utils.messages.BadRequestMessage;
import com.ikubinfo.utils.messages.NotFoundExceptionMessage;
import com.ikubinfo.utils.messages.ValidationMessage;

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
			throw new ValidationException(ValidationMessage.DATA_NOT_VALID);
		}
		SiteEntity site = siteRepository.findOptionalById(cabinToBeCreated.getSite().getId())
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.SITE_NOT_FOUND));
		if (cabinRepository.cabinNumberExists(cabinToBeCreated.getCabinNumber(), site.getId())) {
			throw new ValidationException(ValidationMessage.CABIN_NUMBER_EXISTS);
		}
		List<AttributeEntity> attributeEntities = validateAttributes(cabinToBeCreated);
		CabinEntity cabinEntity = cabinConverter.toEntity(cabinToBeCreated);
		cabinEntity.setSite(site);
		cabinEntity.setCabinAttributes(attributeEntities);
		return cabinConverter.toDto(cabinRepository.save(cabinEntity));
	}

	public CabinDto updateCabin(Integer id, CabinDto cabinToBeUpdated) {
		if (id <= 0) {
			throw new ValidationException(ValidationMessage.ID_NOT_VALID);
		}
		cabinRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.CABIN_NOT_FOUND));
		cabinToBeUpdated.setId(id);
		SiteEntity site = siteRepository.findOptionalById(cabinToBeUpdated.getSite().getId())
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.SITE_NOT_FOUND));
		if (cabinRepository.cabinNumberExists(cabinToBeUpdated.getCabinNumber(), site.getId(), id)) {
			throw new ValidationException(ValidationMessage.CABIN_NUMBER_EXISTS);
		}
		List<AttributeEntity> attributeEntities = validateAttributes(cabinToBeUpdated);
		CabinEntity cabinEntity = cabinConverter.toEntity(cabinToBeUpdated);
		cabinEntity.setSite(site);
		cabinEntity.setCabinAttributes(attributeEntities);
		return cabinConverter.toDto(cabinRepository.update(cabinEntity));
	}

	public List<CabinDto> getCabins(CabinFilter cabinFilter) {
		return cabinConverter.toDtos(cabinRepository.getCabins(cabinFilter));
	}

	public CabinDto getCabinById(Integer id) {
		return cabinConverter.toDto(cabinRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.CABIN_NOT_FOUND)));
	}

	public void deleteCabin(Integer id) {
		CabinEntity cabin = cabinRepository.findOptionalById(id)
				.orElseThrow(() -> new NotFoundException(NotFoundExceptionMessage.CABIN_NOT_FOUND));
		cabinRepository.delete(cabin);
	}

	private List<AttributeEntity> validateAttributes(CabinDto cabin) {
		List<Integer> cabinAttributeIds = cabin.getAttributes().stream().map(AttributeDto::getId)
				.collect(Collectors.toList());
		List<AttributeEntity> cabinAttributes = attributeRepository.findByIdsAndType(cabinAttributeIds,
				AttributeType.CABIN);
		if (cabin.getAttributes().size() != cabinAttributes.size()) {
			throw new BadRequestException(BadRequestMessage.INPUT_INVALID);
		}
		return cabinAttributes;
	}
}
