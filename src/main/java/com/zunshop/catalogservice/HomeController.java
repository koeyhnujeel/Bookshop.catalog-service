package com.zunshop.catalogservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String getGreeting() {
		return "의류 카탈로그에 오신 것을 환영합니다.";
	}
}
