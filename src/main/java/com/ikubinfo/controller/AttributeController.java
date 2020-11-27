package com.ikubinfo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ikubinfo.dto.AttributeDto;
import com.ikubinfo.service.AttributeService;
import com.ikubinfo.utils.Routes;

@RestController
@RequestMapping(value = Routes.ATTRIBUTES, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AttributeController {

	@Autowired
	private AttributeService attributeService;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('CUSTOMER','ADMIN')")
	public ResponseEntity<List<AttributeDto>> getAttributes(
			@RequestParam(value = Routes.TYPE, required = false) String type) {
		return ResponseEntity.ok(attributeService.filterAttributesByType(type));
	}

	@GetMapping(value = Routes.BY_ID)
	@PreAuthorize("hasAnyAuthority('CUSTOMER','ADMIN')")
	public ResponseEntity<AttributeDto> findAttributeById(@PathVariable(value = Routes.ID) int id) {
		return ResponseEntity.ok(attributeService.getAttributeById(id));
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<AttributeDto> createAttribute(@Valid @RequestBody AttributeDto attribute) {
		return ResponseEntity.ok(attributeService.createAttribute(attribute));
	}

	@PutMapping(value = Routes.BY_ID)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<AttributeDto> updateAttribute(@PathVariable(value = Routes.ID) int id,
			@Valid @RequestBody AttributeDto attributeToBeUpdated) {
		return ResponseEntity.ok(attributeService.updateAttribute(id, attributeToBeUpdated));
	}

	@DeleteMapping(value = Routes.BY_ID)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Void> deleteAttribute(@PathVariable(value = Routes.ID) int id) {
		attributeService.deleteAttribute(id);
		return ResponseEntity.noContent().build();
	}
}
