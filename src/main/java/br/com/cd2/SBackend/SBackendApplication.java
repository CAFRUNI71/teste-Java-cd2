package br.com.cd2.SBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SBackendApplication.class, args);
	}

}
