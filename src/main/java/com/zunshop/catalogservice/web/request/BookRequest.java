package com.zunshop.catalogservice.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookRequest {

	@NotBlank(message = "The boot ISBN must be defined.")
	@Pattern(
		regexp = "^([0-9]{10}|[0-9]{13})$",
		message = "The ISBN format must be valid."
	)
	String isbn;

	@NotBlank(message = "The boot title must be defined.")
	String title;

	@NotBlank(message = "The boot author must be defined.")
	String author;

	@NotNull(message = "The book price must be defined.")
	@Positive(message = "The boot price must be greater than zero.")
	Double price;

	@Builder
	public BookRequest(String isbn, String title, String author, Double price) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
	}
}
