package com.sky.pay.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.jupiter.api.Test;

class PrintStatementTests {

	@Test
	void printStatementShouldPrintNoOperationIfThereIsNoOperation() {
		// Arrange
		PrintInerface printInterfaceMock = mock(PrintInerface.class);
		TimePicker timePickerMock = mock(TimePicker.class);
		Account service = new Account(0, printInterfaceMock, timePickerMock);
		String expectedOutput = new StringBuilder()
				.append("Date        || Amount      || Balance     ")
				.append("\n")
				.toString();
		
		// Act
		service.printStatement();
		
		// Assert
		verify(printInterfaceMock, times(1)).print(expectedOutput);
	}
	
	@Test
	void printStatementShouldPrintAllOperationOnAccount() {
		// Arrange
		PrintInerface printInterfaceMock = mock(PrintInerface.class);
		TimePicker timePickerMock = mock(TimePicker.class);
		when(timePickerMock.now()).thenReturn(Instant.ofEpochMilli(1701218919000L));
		Account service = new Account(0, printInterfaceMock, timePickerMock);
		service.deposit(1000);
		service.deposit(2000);
		service.withdraw(500);
		String expectedOutput = new StringBuilder()
				.append("Date        || Amount      || Balance     ")
				.append("\n")
				.append("29/11/2023  || -500        || 2500        ")
				.append("\n")
				.append("29/11/2023  || 2000        || 3000        ")
				.append("\n")
				.append("29/11/2023  || 1000        || 1000        ")
				.append("\n")
				.toString();
		
		// Act
		service.printStatement();
		
		// Assert
		verify(printInterfaceMock, times(1)).print(expectedOutput);
		verify(timePickerMock, times(3)).now();
	}

}
