package com.masai.bank.services;

import com.masai.bank.models.Account;
import com.masai.bank.models.Transaction;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);

    String creditAmount(Long accountId, Transaction transaction);

    String debitAmount(Long accountId, Transaction transaction);

    List<Transaction> getTransactionHistory(Long accountId);
}
