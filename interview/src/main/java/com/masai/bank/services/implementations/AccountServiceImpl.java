package com.masai.bank.services.implementations;

import com.masai.bank.exceptions.InvalidTransactionException;
import com.masai.bank.exceptions.ResourceNotFoundException;
import com.masai.bank.models.*;
import com.masai.bank.repositories.AccountRepository;
import com.masai.bank.repositories.TransactionRepository;
import com.masai.bank.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

//    private final AccountRepository accountRepository;
//    private final TransactionRepository transactionRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionRepository transactionRepository;

//    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
//        this.accountRepository = accountRepository;
//        this.transactionRepository = transactionRepository;
//    }

    @Override
    public Account createAccount(Account account) {
        Account savedAccount = accountRepository.save(account);
        return savedAccount;
    }

    @Override
    public String creditAmount(Long accountId, Transaction transaction) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        transaction.setTransactionType(TransactionType.CREDIT);

        int currentBalance = account.getCurrentBalance();
        int updatedBalance = currentBalance + transaction.getAmount();

        account.setCurrentBalance(updatedBalance);

        transaction.setBalanceAfter(updatedBalance);
        transaction.setDate(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(transaction);

        account.getTransactions().add(savedTransaction);
        accountRepository.save(account);

        return "Credit transaction successful! Transaction id: " + String.valueOf(savedTransaction.getTransactionId());
    }

    @Override
    public String debitAmount(Long accountId, Transaction transaction) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        transaction.setTransactionType(TransactionType.DEBIT);

        int currentBalance = account.getCurrentBalance();
        int updatedBalance = currentBalance - transaction.getAmount();

        if (updatedBalance < 0)
            throw new InvalidTransactionException("Cannot draw more than bank balance");

        account.setCurrentBalance(updatedBalance);
        transaction.setBalanceAfter(updatedBalance);
        transaction.setDate(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(transaction);

        account.getTransactions().add(savedTransaction);
        accountRepository.save(account);

        return "Debit transaction successful! Transaction id: " + String.valueOf(savedTransaction.getTransactionId());
    }

    @Override
    public List<Transaction> getTransactionHistory(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account id not found"));

        return account.getTransactions();
    }
}
