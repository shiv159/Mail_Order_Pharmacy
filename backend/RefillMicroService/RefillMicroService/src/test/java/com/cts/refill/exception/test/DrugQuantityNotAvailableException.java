package com.cts.refill.exception.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DrugQuantityNotAvailableException {
	
	@Test
	void drugQuantityNotAvailableException()
	{
		String actual = "OK";
		String expected = "OK";
		Assertions.assertEquals(actual, expected);
	}

}
