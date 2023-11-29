package com.sky.pay.test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class Account implements AccountService {

	private int balance;
	private PrintInerface printInerface;
	private static final int PADDING = 12;
	private static final String PATTERN_FORMAT = "dd/MM/yyyy";
	private final DateTimeFormatter formatter;
	private final List<Operation> operations;
	
	public Account(int initialBalance, PrintInerface printInerface) {
		balance = initialBalance;
		this.printInerface = printInerface;
		formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
				.withZone(ZoneId.of("UTC"));
		operations = new ArrayList<>();
	}
	
	@Override
	public void deposit(int amount) {
		if(amount <= 0) {
			return;
		}
		balance += amount;
		operations.add(new Operation(Instant.now(), amount, balance));
	}

	@Override
	public void withdraw(int amount) {
		if(amount <= 0) {
			return;
		}
		if(balance < amount) {
			return;
		}
		balance -= amount;
		operations.add(new Operation(Instant.now(), -amount, balance));
	}

	@Override
	public void printStatement() {
		StringBuilder statementBuilder = new StringBuilder();
		AppendRow(statementBuilder, "Date", "Amount", "Balance");
		for(int i = operations.size() - 1; i >= 0; i--) {
			AppendRow(statementBuilder,
					formatter.format(operations.get(i).getDate()),
					operations.get(i).getAmount() + "",
					operations.get(i).getBalance() + "");
		}
		
		printInerface.print(statementBuilder.toString());
		
	}
	
	public int getBalance() {
		return balance;
	}
	
	private void AppendRow(StringBuilder statementBuilder,
			String dateToken,
			String amountToken,
			String balanceToken) {
		final String separator = "|| ";
		statementBuilder.append(padRight(dateToken, ' ', PADDING))
				.append(separator)
				.append(padRight(amountToken, ' ', PADDING))
				.append(separator)
				.append(padRight(balanceToken, ' ', PADDING))
				.append('\n');
	}
	
	private String padRight(String inputString, char caractere, int length) {
	    if (inputString.length() >= length) {
	        return inputString;
	    }
	    StringBuilder sb = new StringBuilder();
	    sb.append(inputString);
	    while (sb.length() < length) {
	        sb.append(caractere);
	    }

	    return sb.toString();
	}
	

	public final class Operation {
		private Instant date;
		private int amount;
		private int balance;
		
		public Operation(Instant date, int amount, int balance) {
			super();
			this.date = date;
			this.amount = amount;
			this.balance = balance;
		}
		
		public Instant getDate() {
			return date;
		}
		
		public int getAmount() {
			return amount;
		}
		
		public int getBalance() {
			return balance;
		}
	}
}


