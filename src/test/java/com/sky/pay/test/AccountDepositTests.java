package com.sky.pay.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AccountDepositTests {

	@ParameterizedTest
	@MethodSource("validDepositsParameters")
	public void depositShouldIncreaseBalance(int initalAmount, int amount) {
		// Arrange
		Account service = new Account(initalAmount);
		
		// Act
		service.deposit(amount);
		
		// Assert
		int actualAmount = service.getBalance();
		assertEquals(initalAmount + amount, actualAmount);
	}
	
	@ParameterizedTest
	@MethodSource("negativeDepositsParameters")
	public void depositNegativeAmountShouldNotImpactBalance(int initalAmount, int amount) {
		// Arrange
		Account service = new Account(initalAmount);
		
		// Act
		service.deposit(amount);
		
		// Assert
		int actualAmount = service.getBalance();
		assertEquals(initalAmount, actualAmount);
	}
	
	@Test
	@Disabled("The requirements does not presise what we should do in that case.")
	public void overflowDepositShouldNotImpactBalance() {
		// Arrange
		Account service = new Account(1);
		
		// Act
		service.deposit(Integer.MAX_VALUE);
		
		// Assert
		int actualAmount = service.getBalance();
		fail("Validate with POO what value should we expected in balance, actually: " + actualAmount);
	}
	
	private static Stream<Arguments> validDepositsParameters() {
	    return Stream.of(
	            Arguments.of(1, 1),
	            Arguments.of(0, 2),
	            Arguments.of(100, 3),
	            Arguments.of(100, 0),
	            Arguments.of(0, 0)
	    );
	}
	
	private static Stream<Arguments> negativeDepositsParameters() {
	    return Stream.of(
	            Arguments.of(1, -1),
	            Arguments.of(0, -2),
	            Arguments.of(100, -3),
	            Arguments.of(100, -0),
	            Arguments.of(0, -100)
	    );
	}

}
