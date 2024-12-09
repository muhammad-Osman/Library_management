package com.self.library.service.impl;

import com.self.library.entity.Book;
import com.self.library.repository.BookRepository;
import com.self.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book registerBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void borrowBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (book.getIsBorrowed()) {
            throw new IllegalStateException("Book is already borrowed");
        }

        book.setIsBorrowed(true);
        bookRepository.save(book);
    }

    @Override
    public void returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (!book.getIsBorrowed()) {
            throw new IllegalStateException("Book is not borrowed");
        }

        book.setIsBorrowed(false);
        bookRepository.save(book);
    }
}
