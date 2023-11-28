package com.sky.pay.test;

public final class Account implements AccountService {

	private int balance;
	
	public Account(int initialBalance) {
		balance = initialBalance;
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
		// TODO Auto-generated method stub
		
	}
	
	public int getBalance() {
		return balance;
	}
}