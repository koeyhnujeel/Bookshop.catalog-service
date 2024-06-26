package com.zunshop.catalogservice.web;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.zunshop.catalogservice.domain.BookNotFoundException;
import com.zunshop.catalogservice.domain.BookService;

@WebMvcTest(BookController.class)
public class BookControllerMvcTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Test
	void whenGetBookNotExistingThenShouldReturn404() throws Exception {
		String isbn = "73737313940";
		given(bookService.viewBookDetails(isbn))
			.willThrow(BookNotFoundException.class);
		mockMvc
			.perform(get("/books/" + isbn))
			.andExpect(status().isNotFound());
	}
}
