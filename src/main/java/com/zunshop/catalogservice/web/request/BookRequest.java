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
	private String isbn;

	@NotBlank(message = "The boot title must be defined.")
	private String title;

	@NotBlank(message = "The boot author must be defined.")
	private String author;

	@NotNull(message = "The book price must be defined.")
	@Positive(message = "The boot price must be greater than zero.")
	private Double price;

	@NotBlank(message = "The book publisher must be defined.")
	private String publisher;

	@Builder
	public BookRequest(String isbn, String title, String author, Double price, String publisher) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.publisher = publisher;
	}
}
