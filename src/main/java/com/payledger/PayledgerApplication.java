package com.payledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication()
public class PayledgerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayledgerApplication.class, args);
		System.out.println("Hello Developers");
		System.out.println("Welcome to the PayLedger");
	}

}
