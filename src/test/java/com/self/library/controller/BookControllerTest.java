package com.self.library.controller;

import com.self.library.entity.Book;
import com.self.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void registerBook_ShouldReturnCreatedBook() throws Exception {
        Book book = new Book(1, "978-3-16-148410-0", "Book Title", "Author");

        when(bookService.registerBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isbn\":\"978-3-16-148410-0\",\"title\":\"Book Title\",\"author\":\"Author\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("978-3-16-148410-0"))
                .andExpect(jsonPath("$.title").value("Book Title"))
                .andExpect(jsonPath("$.author").value("Author"));

        verify(bookService, times(1)).registerBook(any(Book.class));
    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks() throws Exception {
        List<Book> books = Arrays.asList(
                new Book(1, "978-3-16-148410-0", "Book Title 1", "Author 1"),
                new Book(2, "978-1-23-456789-0", "Book Title 2", "Author 2")
        );

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value("978-3-16-148410-0"))
                .andExpect(jsonPath("$[1].isbn").value("978-1-23-456789-0"));

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void borrowBook_ShouldReturnSuccessMessage() throws Exception {
        doNothing().when(bookService).borrowBook(1L, 1L);

        mockMvc.perform(post("/api/books/1/borrow")
                        .param("borrowerId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book borrowed successfully."));

        verify(bookService, times(1)).borrowBook(1L, 1L);
    }
}
