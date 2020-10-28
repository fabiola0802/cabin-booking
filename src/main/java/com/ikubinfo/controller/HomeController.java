package com.ikubinfo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "")
public class HomeController {
	
	@GetMapping(value="/test")
	public String test() {
		return "It Works!";
	}

}
