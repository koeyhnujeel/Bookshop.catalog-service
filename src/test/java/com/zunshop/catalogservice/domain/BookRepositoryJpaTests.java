package com.zunshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.zunshop.catalogservice.config.DataConfig;

/*
 * @DataJpaTest: 스프링 데이터 Jpa 컴포넌트를 집중적으로 테스트하는 클래스임을 나타낸다.
 * @Import(DataConfig.class): 데이터 설정을 임포트한다. (감사를 활성화하기 위해 필요)
 * @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE): 테스트컨테이너를 이용해야 하기 때문에 내장 테스트 데이터베이스 비활성화
 * @ActiveProfiles("integration"): application-integration.yml 프로파일 활성화
 */

@DataJpaTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(
	replace = AutoConfigureTestDatabase.Replace.NONE
)
@ActiveProfiles("integration")
public class BookRepositoryJpaTests {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	void findBookByIsbnWhenExisting() {
		String bookIsbn = "1234561237";
		Book book = Book.builder()
			.isbn(bookIsbn)
			.title("Title")
			.author("Author")
			.price(9.90)
			.build();
		entityManager.persist(book);
		Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

		assertThat(actualBook).isPresent();
		assertThat(actualBook.get().getIsbn()).isEqualTo(bookIsbn);

	}
}
