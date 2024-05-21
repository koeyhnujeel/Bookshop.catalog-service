package com.zunshop.catalogservice.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.zunshop.catalogservice.web.request.BookRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String isbn;

	private String title;

	private String author;

	private Double price;

	private String publisher;

	@CreatedDate
	private Instant createdDate;

	@LastModifiedDate
	private Instant lastModifiedDate;

	@Version
	private int version;

	@Builder
	private Book(String isbn, String title, String author, Double price, String publisher) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.publisher = publisher;
	}

	public static Book from(BookRequest bookRequest) {
		return Book.builder()
			.isbn(bookRequest.getIsbn())
			.title(bookRequest.getTitle())
			.author(bookRequest.getAuthor())
			.price(bookRequest.getPrice())
			.publisher(bookRequest.getPublisher())
			.build();
	}

	public void modifyBook(BookRequest request) {
		this.title = request.getTitle();
		this.author = request.getAuthor();
		this.price = request.getPrice();
		this.publisher = request.getPublisher();
	}
}
