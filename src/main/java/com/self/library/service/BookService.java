package com.self.library.service;

import com.self.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public Book registerBook(Book book);
    public List<Book> getAllBooks();
    public Optional<Book> getBookById(Long id);
    public void borrowBook(Long bookId, Long borrowerId);
    public void returnBook(Long bookId);

}
