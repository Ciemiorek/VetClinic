package com.ciemiorek.artur.vetClinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:message.properties", encoding = "UTF-8")
public class VetClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetClinicApplication.class, args);
	}

}
