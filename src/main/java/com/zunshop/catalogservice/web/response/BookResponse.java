package com.zunshop.catalogservice.web.response;

import com.zunshop.catalogservice.domain.Book;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BookResponse {

	String isbn;
	String title;
	String author;
	Double price;

	@Builder
	private BookResponse(String isbn, String title, String author, Double price) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public static BookResponse from(Book book) {
		return BookResponse.builder()
			.isbn(book.getIsbn())
			.title(book.getTitle())
			.author(book.getAuthor())
			.price(book.getPrice())
			.build();
	}
}
