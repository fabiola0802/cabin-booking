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

import com.ikubinfo.dto.SiteDto;
import com.ikubinfo.dto.SiteFilter;
import com.ikubinfo.service.SiteService;
import com.ikubinfo.utils.Routes;

@RestController
@RequestMapping(value = Routes.SITES, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SiteController {

	@Autowired
	private SiteService siteService;

	@GetMapping
	public ResponseEntity<List<SiteDto>> getSites(
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "location", required = false) String location) {
		SiteFilter sitefilter = new SiteFilter(description, location);
		return ResponseEntity.ok(siteService.getSites(sitefilter));
	}

	@GetMapping(value = Routes.BY_ID)
	public ResponseEntity<SiteDto> getSiteById(@PathVariable(value = Routes.ID) int id) {
		return ResponseEntity.ok(siteService.findSiteById(id));

	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<SiteDto> addSite(@Valid @RequestBody SiteDto siteToBeCreated) {
		return ResponseEntity.ok(siteService.addSite(siteToBeCreated));
	}

	@PutMapping(value = Routes.BY_ID)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<SiteDto> updateSite(@PathVariable(value = Routes.ID) int id,
			@Valid @RequestBody SiteDto siteToBeUpdated) {
		return ResponseEntity.ok(siteService.updateSite(id, siteToBeUpdated));
	}

	@DeleteMapping(value = Routes.BY_ID)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Void> deleteSite(@PathVariable(value = Routes.ID) int id) {
		siteService.deleteSite(id);
		return ResponseEntity.noContent().build();
	}
}
