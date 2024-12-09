package com.self.library.controller;

import com.self.library.entity.Book;
import com.self.library.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> registerBook(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.registerBook(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/{bookId}/borrow")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @RequestParam Long borrowerId) {
        bookService.borrowBook(bookId, borrowerId);
        return ResponseEntity.ok("Book borrowed successfully.");
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        bookService.returnBook(bookId);
        return ResponseEntity.ok("Book returned successfully.");
    }
}
