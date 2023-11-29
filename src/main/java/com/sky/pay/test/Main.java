package com.sky.pay.test;

public class Main {
	public static void main(String[] args) {
		AccountService service = new Account(0,
				content -> System.out.println(content),
				new CustumTimePicker());
		service.deposit(1000);
		service.deposit(23000);
		service.withdraw(0);
		service.withdraw(500);
		service.withdraw(1000);
		service.printStatement();
	}
}
