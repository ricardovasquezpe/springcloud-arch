package com.revendedor.ticketservice;

import com.revendedor.ticketservice.config.FeignErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
//@EnableHystrix
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

	@Bean
	public ErrorDecoder errorDecoder() {
		return new FeignErrorDecoder();
	}
}
