package com.sky.pay.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AccountWithdrawTests {

	@ParameterizedTest
	@MethodSource("validWithdrawParameters")
	public void withdrawShouldDecreaseBalance(int initialBalance, int amount) {
		// Arrange
		PrintInerface mock = mock(PrintInerface.class);
		Account service = new Account(initialBalance, mock);
		
		// Act
		service.withdraw(amount);
		
		// Assert
		int actualAmount = service.getBalance();
		assertEquals(initialBalance - amount, actualAmount);
	}
	
	@ParameterizedTest
	@MethodSource("negativeWithdrawParameters")
	public void withdrawNegativeAmountShouldNotImpactBalance(int initialBalance, int amount) {
		// Arrange
		PrintInerface mock = mock(PrintInerface.class);
		Account service = new Account(initialBalance, mock);
		
		// Act
		service.withdraw(amount);
		
		// Assert
		int actualAmount = service.getBalance();
		assertEquals(initialBalance, actualAmount);
	}
	
	@Test
	public void balanceShouldNotChangeWhenWithdrawAmountIsGreaterThanBalance() {
		// Arrange
		int initialBalance = 10;
		PrintInerface mock = mock(PrintInerface.class);
		Account service = new Account(initialBalance, mock);
		
		// Act
		service.withdraw(11);
		
		// Assert
		int actualAmount = service.getBalance();
		assertEquals(initialBalance, actualAmount);
	}
	
	private static Stream<Arguments> validWithdrawParameters() {
	    return Stream.of(
	            Arguments.of(100, 10),
	            Arguments.of(50, 2),
	            Arguments.of(3, 3),
	            Arguments.of(2, 1),
	            Arguments.of(0, 0)
	    );
	}
	
	private static Stream<Arguments> negativeWithdrawParameters() {
	    return Stream.of(
	            Arguments.of(1, -1),
	            Arguments.of(0, -2),
	            Arguments.of(100, -3),
	            Arguments.of(100, -0),
	            Arguments.of(0, -100)
	    );
	}

}
