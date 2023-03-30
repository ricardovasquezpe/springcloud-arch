package com.revendedor.ticketservice.service.impl;

import com.revendedor.ticketservice.application.dto.TicketDto;
import com.revendedor.ticketservice.application.dto.UserDto;
import com.revendedor.ticketservice.application.mapper.TicketMapper;
import com.revendedor.ticketservice.repository.TicketRepository;
import com.revendedor.ticketservice.repository.entity.Ticket;
import com.revendedor.ticketservice.service.interf.APIClient;
import com.revendedor.ticketservice.service.interf.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketMapper ticketMapper;

    /*@Autowired
    RestTemplate restTemplate;*/

    /*@Autowired
    WebClient webClient;*/

    @Autowired
    APIClient apiClient;

    @Override
    public List<TicketDto> findAll() {
       return ticketRepository.findAll().stream().map(ticketMapper::fromEntityToDto).collect(Collectors.toList());
    }

    @Override
    /*@HystrixCommand(
            fallbackMethod = "defaultFindById",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10")
            }
    )*/
    public TicketDto findById(int id) {
        Ticket ticket = ticketRepository.findById(id).get();

        /*ResponseEntity<UserDto> response =
                restTemplate.getForEntity("http://localhost:8080/findById/" + ticket.getUserId(), UserDto.class);
        UserDto dto = response.getBody();*/

        /*UserDto dto = webClient.get()
                .uri("http://localhost:8080/findById/" + ticket.getUserId())
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();*/

        UserDto dto = apiClient.findById(ticket.getUserId());

        TicketDto ticketDto = ticketMapper.fromEntityToDto(ticket);
        ticketDto.setUserName(dto.getName());
        return ticketDto;
    }

    /*public TicketDto defaultFindById(int id){
        Ticket ticket = ticketRepository.findById(id).get();
        TicketDto ticketDto = ticketMapper.fromEntityToDto(ticket);
        ticketDto.setUserName("");

        return ticketDto;
    }*/
}
