package io.crypto.beer.telegram.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@EnableScheduling
public class CryptobeerApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();

		SpringApplication.run(CryptobeerApplication.class, args);
	}

}
