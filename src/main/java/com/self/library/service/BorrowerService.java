package com.self.library.service;

import com.self.library.entity.Borrower;

import java.util.Optional;

public interface BorrowerService {
    public Borrower registerBorrower(Borrower borrower);
    public Optional<Borrower> getBorrowerById(Long id);
}
