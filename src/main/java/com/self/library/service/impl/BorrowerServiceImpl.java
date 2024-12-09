package com.self.library.service.impl;

import com.self.library.entity.Borrower;
import com.self.library.repository.BorrowerRepository;
import com.self.library.service.BorrowerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BorrowerServiceImpl implements BorrowerService {

    private final BorrowerRepository borrowerRepository;

    @Override
    public Borrower registerBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

    @Override
    public Optional<Borrower> getBorrowerById(Long id) {
        return borrowerRepository.findById(id);
    }

}
