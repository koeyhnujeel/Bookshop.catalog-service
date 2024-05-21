package com.zunshop.catalogservice.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zunshop.catalogservice.domain.BookService;
import com.zunshop.catalogservice.web.request.BookRequest;
import com.zunshop.catalogservice.web.response.BookResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@GetMapping
	public List<BookResponse> get() {
		return bookService.viewBookList();
	}

	@GetMapping("{isbn}")
	public BookResponse getByIsbn(@PathVariable String isbn) {
		return bookService.viewBookDetails(isbn);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void post(@Valid @RequestBody BookRequest request) {
		bookService.addBookToCatalog(request);
	}

	@DeleteMapping("{isbn}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String isbn) {
		bookService.removeBookFromCatalog(isbn);
	}

	@PutMapping("{isbn}")
	public void put(@PathVariable String isbn, @Valid @RequestBody BookRequest request) {
		bookService.modifyBookDetails(isbn, request);
	}
}
