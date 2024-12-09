package com.self.library.controller;

import com.self.library.controller.BorrowerController;
import com.self.library.entity.Borrower;
import com.self.library.service.BorrowerService;
import com.self.library.entity.Borrower;
import com.self.library.service.BorrowerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BorrowerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BorrowerService borrowerService;

    @InjectMocks
    private BorrowerController borrowerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(borrowerController).build();
    }

    @Test
    void registerBorrower_ShouldReturnCreatedBorrower() throws Exception {
        Borrower borrower = new Borrower("John Doe", "john.doe@example.com");

        when(borrowerService.registerBorrower(any(Borrower.class))).thenReturn(borrower);

        mockMvc.perform(post("/api/borrowers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(borrowerService, times(1)).registerBorrower(any(Borrower.class));
    }

    @Test
    void getBorrowerById_ShouldReturnBorrower_WhenExists() throws Exception {
        Borrower borrower = new Borrower("Jane Smith", "jane.smith@example.com");
        borrower.setId(1L);

        when(borrowerService.getBorrowerById(1L)).thenReturn(Optional.of(borrower));

        mockMvc.perform(get("/api/borrowers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Smith"))
                .andExpect(jsonPath("$.email").value("jane.smith@example.com"));

        verify(borrowerService, times(1)).getBorrowerById(1L);
    }

    @Test
    void getBorrowerById_ShouldReturnNotFound_WhenDoesNotExist() throws Exception {
        when(borrowerService.getBorrowerById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/borrowers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Borrower not found"));

        verify(borrowerService, times(1)).getBorrowerById(1L);
    }
}
