package com.revendedor.ticketservice.service.interf;

import com.revendedor.ticketservice.application.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080", value = "USER-SERVICE")
public interface APIClient {
    @GetMapping("/findById/{userId}")
    UserDto findById(@PathVariable("userId") int userId);
}
