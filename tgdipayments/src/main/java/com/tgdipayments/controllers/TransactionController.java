package com.tgdipayments.controllers;

import com.tgdipayments.domain.transaction.Transaction;
import com.tgdipayments.dtos.TransactionDTO;
import com.tgdipayments.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        Transaction newTranscation = this.transactionService.generateTransaction(transaction);
        return new ResponseEntity<>(newTranscation, HttpStatus.OK);
    }
}
