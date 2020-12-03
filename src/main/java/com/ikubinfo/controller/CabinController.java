package com.ikubinfo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.ikubinfo.dto.CabinDto;
import com.ikubinfo.dto.CabinFilter;
import com.ikubinfo.service.CabinService;
import com.ikubinfo.utils.Routes;

@RestController
@RequestMapping(value = Routes.CABINS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CabinController {

	@Autowired
	private CabinService cabinService;

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CabinDto> createCabin(@Valid @RequestBody CabinDto cabin) {
		return ResponseEntity.ok(cabinService.createCabin(cabin));
	}

	@GetMapping
	public ResponseEntity<List<CabinDto>> getCabins(
			@RequestParam(value = "numberOfFloors", required = false) Integer numberOfFloors,
			@RequestParam(value = "numberOfKitchens", required = false) Integer numberOfKitchens,
			@RequestParam(value = "numberOfBathrooms", required = false) Integer numberOfBathrooms,
			@RequestParam(value = "numberOfBedrooms", required = false) Integer numberOfBedrooms,
			@RequestParam(value = "maxCapacity", required = false) Integer maxCapacity,
			@RequestParam(value = "price", required = false) Double price,
			@RequestParam(value = "siteId", required = false) Integer siteId,
			@RequestParam(value = "attributeIds", required = false) List<Integer> attributeIds,
			@RequestParam(value = "freeFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate freeFrom,
			@RequestParam(value = "freeTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate freeTo) {

		CabinFilter cabinFilter = new CabinFilter(numberOfFloors, numberOfKitchens, numberOfBathrooms, numberOfBedrooms,
				maxCapacity, price);
		cabinFilter.setAttributeIds(attributeIds);
		cabinFilter.setSiteId(siteId);
		cabinFilter.setFreeFrom(freeFrom);
		cabinFilter.setFreeTo(freeTo);
		return ResponseEntity.ok(cabinService.getCabins(cabinFilter));
	}

	@DeleteMapping(value = Routes.BY_ID)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Void> deleteCabin(@PathVariable(value = Routes.ID) Integer id) {
		cabinService.deleteCabin(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = Routes.BY_ID)
	public ResponseEntity<CabinDto> getCabinById(@PathVariable(value = Routes.ID) Integer id) {
		return ResponseEntity.ok(cabinService.getCabinById(id));
	}

	@PutMapping(value = Routes.BY_ID)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CabinDto> updateCabin(@PathVariable(value = Routes.ID) Integer id,
			@Valid @RequestBody CabinDto cabinToBeUpdated) {
		return ResponseEntity.ok(cabinService.updateCabin(id, cabinToBeUpdated));
	}
}
