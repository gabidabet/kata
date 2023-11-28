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
				.append("Date 				|| Amount				|| Balance						")
				.toString();
		
		// Act
		service.printStatement();
		
		// Assert
		verify(mock, times(1)).print(expectedOutput);
	}

}
