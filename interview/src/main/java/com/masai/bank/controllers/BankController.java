package com.masai.bank.controllers;

import com.masai.bank.models.Account;
import com.masai.bank.models.Transaction;
import com.masai.bank.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class BankController {

//    private final AccountService accountService;
	@Autowired
	AccountService accountService;

//    public BankController(AccountService accountService) {
//        this.accountService = accountService;
//    }

    /**
     *
        localhost:8088/account/create

        {
            "accountDetails": {
                "name": "Aarthi",
                "proofType": "AADHAR",
                "idProof": "1234567890",
                "address": {
                    "city": "Mumbai",
                    "state": "Maharashtra",
                    "zipCode": 76
                }
            },
            "currentBalance": 15000
        }

    * */
    @PostMapping("/account/create")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        Account response = accountService.createAccount(account);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /*
        localhost:8088/transaction/credit/1

        {
            "amount": 5000,
            "message": "bank transfer"
        }

    * */
    @PostMapping("/transaction/credit/{accountId}")
    public ResponseEntity<String> creditAmount(
            @Valid @RequestBody Transaction transaction,
            @NotNull @PathVariable Long accountId
    ) {
        String response = accountService.creditAmount(accountId, transaction);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    /*
        localhost:8088/transaction/debit/1

        {
            "amount": 5000,
            "message": "ordered food online"
        }

    * */
    @PostMapping("/transaction/debit/{accountId}")
    public ResponseEntity<String> debitAmount(
            @Valid @RequestBody Transaction transaction,
            @NotNull @PathVariable Long accountId
    ) {
        String response = accountService.debitAmount(accountId, transaction);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    /*
        localhost:8080/transaction/1

    * */
    @GetMapping("/transaction/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@NotNull @PathVariable Long accountId) {
        List<Transaction> history = accountService.getTransactionHistory(accountId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
    
    @GetMapping("/getBalance/{accountId}")
    public ResponseEntity<String> checkBalance(@PathVariable Long accountId){
    String balance = accountService.checkBalance(accountId);
    return new ResponseEntity<>(balance, HttpStatus.OK);    
    }
    
}
