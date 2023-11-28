package com.sky.pay.test;

public final class Account implements AccountService {

	private int balance;
	
	public Account(int initialBalance) {
		balance = initialBalance;
	}
	
	@Override
	public void deposit(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printStatement() {
		// TODO Auto-generated method stub
		
	}
	
	public int getBalance() {
		return balance;
	}
}