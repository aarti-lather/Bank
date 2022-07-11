package com.masai.bank.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    private TransactionType transactionType;

    private Integer amount;

    private String message;

    private Integer balanceAfter;

    private LocalDateTime date;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getBalanceAfter() {
		return balanceAfter;
	}

	public void setBalanceAfter(Integer balanceAfter) {
		this.balanceAfter = balanceAfter;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	
	
	
	public Transaction() {
		super();
	}

	public Transaction(TransactionType transactionType, Integer amount, String message, Integer balanceAfter,
			LocalDateTime date) {
		super();
		this.transactionType = transactionType;
		this.amount = amount;
		this.message = message;
		this.balanceAfter = balanceAfter;
		this.date = date;
	}
    
    
    
}
