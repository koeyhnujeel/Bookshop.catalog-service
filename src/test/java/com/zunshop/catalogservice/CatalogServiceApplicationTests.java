package com.zunshop.catalogservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.zunshop.catalogservice.web.request.BookRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenBookCreated() {
		BookRequest request = BookRequest.builder()
			.isbn("1234567890")
			.title("title")
			.author("author")
			.price(9.90)
			.publisher("publisher")
			.build();

		webTestClient
			.post()
			.uri("/books")
			.bodyValue(request)
			.exchange()
			.expectStatus().isCreated()
			.expectBody().isEmpty();
	}
}
