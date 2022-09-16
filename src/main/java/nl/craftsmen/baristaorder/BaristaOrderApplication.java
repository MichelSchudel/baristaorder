package nl.craftsmen.baristaorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class BaristaOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaristaOrderApplication.class, args);
	}

}
