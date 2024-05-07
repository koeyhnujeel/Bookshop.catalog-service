package com.zunshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zunshop.catalogservice.web.request.BookRequest;

@ExtendWith(MockitoExtension.class)
class BookServiceTests {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookService bookService;

	@Test
	void whenBookToCreateAlreadyExistsThenThrows() {
		String bookIsbn = "1234561232";
		BookRequest bookToCreate = new BookRequest(bookIsbn, "Title", "Author", 9.90);
		when(bookRepository.existsByIsbn(bookIsbn)).thenReturn(true);
		assertThatThrownBy(() -> bookService.addBookToCatalog(bookToCreate))
			.isInstanceOf(BookAlreadyExistsException.class)
			.hasMessage("A book with ISBN " + bookIsbn + " already exists.");
	}

	@Test
	void whenBookToReadDoesNotExistThenThrows() {
		String bookIsbn = "1234561232";
		when(bookRepository.findByIsbn(bookIsbn)).thenReturn(Optional.empty());
		assertThatThrownBy(() -> bookService.viewBookDetails(bookIsbn))
			.isInstanceOf(BookNotFoundException.class)
			.hasMessage("The book with ISBN " + bookIsbn + " was not found.");
	}

}
