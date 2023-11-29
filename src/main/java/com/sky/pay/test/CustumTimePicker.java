package com.sky.pay.test;

import java.time.Instant;

public final class CustumTimePicker implements TimePicker{

	@Override
	public Instant now() {
		return Instant.now();
	}
	
}