package com.revendedor.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TicketServiceApplication {

	/*@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}*/

	/*@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}*/

	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApplication.class, args);
	}

}
