package com.zunshop.catalogservice.domain;

import com.zunshop.catalogservice.web.request.BookRequest;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Book {

	String isbn;
	String title;
	String author;
	Double price;

	@Builder
	private Book(String isbn, String title, String author, Double price) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public static Book from(BookRequest bookRequest) {
		return Book.builder()
			.isbn(bookRequest.getIsbn())
			.title(bookRequest.getTitle())
			.author(bookRequest.getAuthor())
			.price(bookRequest.getPrice())
			.build();
	}

	public Book modifyBook(BookRequest request) {
		this.title = request.getTitle();
		this.author = request.getAuthor();
		this.price = request.getPrice();
		return this;
	}
}
