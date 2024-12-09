package com.self.library.repository;

import com.self.library.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldSaveAndRetrieveBook() {
        Book book = new Book(1, "978-3-16-148410-0", "Effective Java", "Joshua Bloch");

        // Act
        Book savedBook = bookRepository.save(book);
        Optional<Book> retrievedBook = bookRepository.findById(savedBook.getId());

        // Assert
        assertThat(retrievedBook).isPresent();
        assertThat(retrievedBook.get().getIsbn()).isEqualTo("978-3-16-148410-0");
        assertThat(retrievedBook.get().getTitle()).isEqualTo("Effective Java");
        assertThat(retrievedBook.get().getAuthor()).isEqualTo("Joshua Bloch");
    }

    @Test
    void shouldReturnBooksByIsbn() {
        // Arrange
        Book book1 = new Book(1, "978-3-16-148410-0", "Effective Java", "Joshua Bloch");
        Book book2 = new Book(2, "978-3-16-148410-0", "Effective Java", "Joshua Bloch");
        Book book3 = new Book(3, "978-1-23-456789-0", "Clean Code", "Robert C. Martin");

        bookRepository.saveAll(List.of(book1, book2, book3));

        Optional<Book> retrievedBook = bookRepository.findByIsbn("978-3-16-148410-0");

        assertThat(retrievedBook).isPresent();
        assertThat(retrievedBook.get().getIsbn()).isEqualTo("978-3-16-148410-0");
    }

    @Test
    void shouldReturnEmptyList_WhenNoBooksByIsbnFound() {
        // Act
        Optional<Book> booksByIsbn = bookRepository.findByIsbn("978-0-00-000000-0");

        // Assert
        assertThat(booksByIsbn).isEmpty();
    }

    @Test
    void shouldUpdateBookStatus() {
        // Arrange
        Book book = new Book(1, "978-3-16-148410-0", "Effective Java", "Joshua Bloch");
        Book savedBook = bookRepository.save(book);

        // Act
        savedBook.setIsBorrowed(true);
        bookRepository.save(savedBook);

        Optional<Book> updatedBook = bookRepository.findById(savedBook.getId());

        // Assert
        assertThat(updatedBook).isPresent();
        assertThat(updatedBook.get().getIsBorrowed()).isTrue();
    }
}
