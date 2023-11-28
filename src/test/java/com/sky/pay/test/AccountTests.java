package com.sky.pay.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AccountTests {

	@ParameterizedTest
	@MethodSource("provideParameters")
	public void DepositShouldIncreaseBalance(int initalAmount, int amount) {
		// Arrange
		Account service = new Account(initalAmount);
		
		// Act
		service.deposit(amount);
		
		// Assert
		int actualAmount = service.getBalance();
		assertEquals(initalAmount + amount, actualAmount);
	}
	
	private static Stream<Arguments> provideParameters() {
	    return Stream.of(
	            Arguments.of(1, 1),
	            Arguments.of(0, 2),
	            Arguments.of(100, 3)
	    );
	}

}
