package com.zunshop.catalogservice.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zunshop.catalogservice.web.request.BookRequest;
import com.zunshop.catalogservice.web.response.BookResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	// public List<BookResponse> viewBookList() {
	// 	return bookRepository.findAll().stream()
	// 		.map(book -> BookResponse.from(book))
	// 		.collect(Collectors.toList());
	// }

	public BookResponse viewBookDetails(String isbn) {
		Book book = bookRepository.findByIsbn(isbn)
			.orElseThrow(() -> new BookNotFoundException(isbn));

		return BookResponse.from(book);
	}

	public void addBookToCatalog(BookRequest request) {
		if (bookRepository.existsByIsbn(request.getIsbn())) {
			throw new BookAlreadyExistsException(request.getIsbn());
		}

		Book book = Book.from(request);
		bookRepository.save(book);
	}

	public void removeBookFromCatalog(String isbn) {
		bookRepository.deleteByIsbn(isbn);
	}

	public void modifyBookDetails(String isbn, BookRequest request) {
		bookRepository.findByIsbn(isbn)
			.ifPresentOrElse(
				existingBook -> existingBook.modifyBook(request),
				() -> addBookToCatalog(request)
			);
	}
}
