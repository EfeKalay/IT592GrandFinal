package edu.sabanciuniv.ipamdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class IpamdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpamdemoApplication.class, args);
	}

}
