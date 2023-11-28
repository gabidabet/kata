package com.sky.pay.test;

public interface AccountService {
	void deposit(int amount);
	void withdraw(int amount);
	void printStatement();
}