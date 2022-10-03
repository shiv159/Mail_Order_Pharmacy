package com.mailorderpharmacy.subscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class SubscriptionServiceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(SubscriptionServiceApplication.class, args);
	}

}
