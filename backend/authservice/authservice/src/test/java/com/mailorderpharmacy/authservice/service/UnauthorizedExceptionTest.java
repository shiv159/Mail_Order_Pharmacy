package com.mailorderpharmacy.authservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class UnauthorizedExceptionTest {
	@Test
	 void constructortest()
	{
		UnauthorizedException unauthorizedException =new UnauthorizedException("unauthorized");
		assertEquals("unauthorized", unauthorizedException.getMessage());
	}

}