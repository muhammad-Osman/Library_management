package com.self.library.repository;

import com.self.library.entity.Borrower;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BorrowerRepositoryTest {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Test
    void shouldSaveAndRetrieveBorrower() {
        Borrower borrower = new Borrower("John Doe", "john.doe@example.com");
        Borrower savedBorrower = borrowerRepository.save(borrower);

        Optional<Borrower> retrievedBorrower = borrowerRepository.findById(savedBorrower.getId());

        assertThat(retrievedBorrower).isPresent();
        assertThat(retrievedBorrower.get().getName()).isEqualTo("John Doe");
        assertThat(retrievedBorrower.get().getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    void shouldReturnEmpty_WhenBorrowerDoesNotExist() {
        Optional<Borrower> retrievedBorrower = borrowerRepository.findById(1L);
        assertThat(retrievedBorrower).isEmpty();
    }
}
