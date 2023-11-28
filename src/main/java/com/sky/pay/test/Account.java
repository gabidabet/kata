package com.sky.pay.test;

public final class Account implements AccountService {

	private int balance;
	private PrintInerface printInerface;
	
	public Account(int initialBalance, PrintInerface printInerface) {
		balance = initialBalance;
		this.printInerface = printInerface;
	}
	
	@Override
	public void deposit(int amount) {
		if(amount <= 0) {
			return;
		}
		balance += amount;
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
	}

	@Override
	public void printStatement() {
		printInerface.print("");
		
	}
	
	public int getBalance() {
		return balance;
	}
}