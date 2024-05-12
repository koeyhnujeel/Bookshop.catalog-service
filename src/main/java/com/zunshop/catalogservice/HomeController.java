package com.zunshop.catalogservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zunshop.catalogservice.config.ZunProperties;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeController {

	private final ZunProperties zunProperties;

	@GetMapping("/")
	public String getGreeting() {
		return zunProperties.getGreeting();
	}
}
