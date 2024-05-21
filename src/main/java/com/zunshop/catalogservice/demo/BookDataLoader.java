package com.zunshop.catalogservice.demo;

import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.zunshop.catalogservice.domain.Book;
import com.zunshop.catalogservice.domain.BookRepository;

import lombok.RequiredArgsConstructor;

@Component
@Profile("testdata")
@RequiredArgsConstructor
public class BookDataLoader {

	private final BookRepository bookRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void loadBookData() {
		Book book1 = Book.builder()
			.isbn("1234567891")
			.title("Northern Lights")
			.author("Lyra Silverstar")
			.price(9.90)
			.build();

		Book book2 = Book.builder()
			.isbn("1234567892")
			.title("Polar Journey")
			.author("Iorek Polarson")
			.price(12.90)
			.build();

		bookRepository.saveAll(List.of(book1, book2));
	}
}
