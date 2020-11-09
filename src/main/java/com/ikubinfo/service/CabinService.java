package com.ikubinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.converter.CabinConverter;
import com.ikubinfo.dto.AttributeDto;
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
		CabinEntity cabinEntity = cabinConverter.toEntity(cabinToBeCreated);
		cabinEntity.setSite(site);
		List<Integer> attributesId = cabinToBeCreated.getAttributes().stream().map(AttributeDto::getId)
				.collect(Collectors.toList());
		List<AttributeEntity> attributeEntities = new ArrayList<>();
		attributesId.stream().forEach(attributeId -> {
			AttributeEntity attribute = attributeRepository.findOptionalById(attributeId)
					.orElseThrow(() -> new NotFoundException(ExceptionMessage.ATTRIBUTE_NOT_FOUND.getMessage()));
			if (!attribute.getType().equals(AttributeType.CABIN)) {
				throw new BadRequestException(BadRequestMessage.INCORRECT_TYPE.getMessage());
			}
			attributeEntities.add(attribute);
		});
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
		CabinEntity cabinEntity = cabinConverter.toEntity(cabinToBeUpdated);
		cabinEntity.setSite(site);
		List<Integer> attributesId = cabinToBeUpdated.getAttributes().stream().map(AttributeDto::getId)
				.collect(Collectors.toList());
		List<AttributeEntity> attributeEntities = new ArrayList<>();
		attributesId.stream().forEach(attributeId -> {
			AttributeEntity attribute = attributeRepository.findOptionalById(attributeId)
					.orElseThrow(() -> new NotFoundException(ExceptionMessage.ATTRIBUTE_NOT_FOUND.getMessage()));
			if (!attribute.getType().equals(AttributeType.CABIN)) {
				throw new BadRequestException(BadRequestMessage.INCORRECT_TYPE.getMessage());
			}
			attributeEntities.add(attribute);
		});
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

}
