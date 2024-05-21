package com.zunshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.zunshop.catalogservice.web.request.BookRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class BookValidationTests {
	private static Validator validator;

	@BeforeAll
	static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void whenAllFieldsCorrectThenValidationSucceeds() {
		// given
		BookRequest request = BookRequest.builder()
			.isbn("1234567890")
			.title("title")
			.author("author")
			.price(9.90)
			.publisher("publisher")
			.build();

		// expected
		Set<ConstraintViolation<BookRequest>> violations = validator.validate(request);
		assertThat(violations).isEmpty();
	}

	@Test
	void whenIsbnDefinedButIncorrectThenValidationFails() {
		// given
		BookRequest request = BookRequest.builder()
			.isbn("a234567890")
			.title("title")
			.author("author")
			.price(9.90)
			.publisher("publisher")
			.build();

		// expected
		Set<ConstraintViolation<BookRequest>> violations = validator.validate(request);
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage())
			.isEqualTo("The ISBN format must be valid.");
	}
}
