package com.self.library.controller;

import com.self.library.entity.Borrower;
import com.self.library.service.BorrowerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrowers")
@AllArgsConstructor
public class BorrowerController {

    private BorrowerService borrowerService;

    @PostMapping
    public ResponseEntity<Borrower> registerBorrower(@Valid @RequestBody Borrower borrower) {
        return ResponseEntity.ok(borrowerService.registerBorrower(borrower));
    }

}