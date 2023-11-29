package com.sky.pay.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class PrintStatementTests {

	@Test
	void printStatementShouldPrintNoOperationIfThereIsNoOperation() {
		// Arrange
		PrintInerface mock = mock(PrintInerface.class);
		Account service = new Account(0, mock);
		String expectedOutput = new StringBuilder()
				.append("Date        || Amount      || Balance     ")
				.append("\n")
				.toString();
		
		// Act
		service.printStatement();
		
		// Assert
		verify(mock, times(1)).print(expectedOutput);
	}
	
	@Test
	void printStatementShouldPrintAllOperationOnAccount() {
		// Arrange
		PrintInerface mock = mock(PrintInerface.class);
		Account service = new Account(0, mock);
		service.deposit(1000);
		service.deposit(2000);
		service.withdraw(500);
		String expectedOutput = new StringBuilder()
				.append("Date        || Amount      || Balance     ")
				.append("\n")
				.append("29/08/2023  || -500        || 2500        ")
				.append("\n")
				.append("29/08/2023  || 2000        || 3000        ")
				.append("\n")
				.append("29/08/2023  || 1000        || 1000        ")
				.append("\n")
				.toString();
		
		// Act
		service.printStatement();
		
		// Assert
		verify(mock, times(1)).print(expectedOutput);
	}

}
