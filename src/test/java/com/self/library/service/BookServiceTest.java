package com.self.library.service;

import com.self.library.entity.Book;
import com.self.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerBook_ShouldSaveBook() {
        Book book = new Book(1, "978-3-16-148410-0", "Book Title 1", "Author 1");
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.registerBook(book);

        assertNotNull(result);
        assertEquals("978-3-16-148410-0", result.getIsbn());
        assertEquals("Book Title 1", result.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void getAllBooks_ShouldReturnBooks() {
        List<Book> books = Arrays.asList(
                new Book(1, "978-3-16-148410-0", "Book Title 1", "Author 1"),
                new Book(2, "978-1-23-456789-0", "Book Title 2", "Author 2")
        );

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void borrowBook_ShouldSetIsBorrowedToTrue_WhenAvailable() {
        Book book = new Book(1, "978-3-16-148410-0", "Book Title 1", "Author 1");
        book.setId(1L);
        book.setIsBorrowed(false);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        bookService.borrowBook(1L, 1L);

        assertTrue(book.getIsBorrowed());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void borrowBook_ShouldThrowException_WhenAlreadyBorrowed() {
        Book book = new Book(1, "978-3-16-148410-0", "Book Title 1", "Author 1");
        book.setId(1L);
        book.setIsBorrowed(true);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            bookService.borrowBook(1L, 1L);
        });

        assertEquals("Book is already borrowed", exception.getMessage());
        verify(bookRepository, times(0)).save(book);
    }

    @Test
    void returnBook_ShouldSetIsBorrowedToFalse_WhenBorrowed() {
        Book book = new Book(1, "978-3-16-148410-0", "Book Title 1", "Author 1");
        book.setId(1L);
        book.setIsBorrowed(true);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        bookService.returnBook(1L);

        assertFalse(book.getIsBorrowed());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void returnBook_ShouldThrowException_WhenNotBorrowed() {
        Book book = new Book(1, "978-3-16-148410-0", "Book Title 1", "Author 1");
        book.setId(1L);
        book.setIsBorrowed(false);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            bookService.returnBook(1L);
        });

        assertEquals("Book is not borrowed", exception.getMessage());
        verify(bookRepository, times(0)).save(book);
    }
}
