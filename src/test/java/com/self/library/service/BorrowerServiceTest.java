package com.self.library.service;

import com.self.library.entity.Borrower;
import com.self.library.repository.BorrowerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BorrowerServiceTest {

    @Mock
    private BorrowerRepository borrowerRepository;

    @InjectMocks
    private BorrowerService borrowerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerBorrower_ShouldSaveBorrower() {
        Borrower borrower = new Borrower("John Doe", "john.doe@example.com");
        when(borrowerRepository.save(borrower)).thenReturn(borrower);

        Borrower result = borrowerService.registerBorrower(borrower);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
        verify(borrowerRepository, times(1)).save(borrower);
    }

    @Test
    void getBorrowerById_ShouldReturnBorrower_WhenExists() {
        Borrower borrower = new Borrower("Jane Smith", "jane.smith@example.com");
        borrower.setId(1L);

        when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));

        Optional<Borrower> result = borrowerService.getBorrowerById(1L);

        assertTrue(result.isPresent());
        assertEquals("Jane Smith", result.get().getName());
        verify(borrowerRepository, times(1)).findById(1L);
    }

    @Test
    void getBorrowerById_ShouldReturnEmpty_WhenNotExists() {
        when(borrowerRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Borrower> result = borrowerService.getBorrowerById(1L);

        assertFalse(result.isPresent());
        verify(borrowerRepository, times(1)).findById(1L);
    }
}
