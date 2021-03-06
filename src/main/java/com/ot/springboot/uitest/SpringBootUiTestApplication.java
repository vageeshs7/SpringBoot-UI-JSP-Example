package com.ot.springboot.uitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringBootUiTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUiTestApplication.class, args);
	}

}
