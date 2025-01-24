package com.gazaltech.meta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MetaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetaApplication.class, args);
	}

}
