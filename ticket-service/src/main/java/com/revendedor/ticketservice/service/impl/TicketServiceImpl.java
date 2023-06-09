package com.revendedor.ticketservice.service.impl;

import com.revendedor.basedomains.application.dto.UserDto;
import com.revendedor.ticketservice.api.request.CreateTicketRequest;
import com.revendedor.ticketservice.application.dto.TicketDto;
import com.revendedor.ticketservice.application.mapper.TicketMapper;
import com.revendedor.ticketservice.repository.TicketRepository;
import com.revendedor.ticketservice.repository.entity.Ticket;
import com.revendedor.ticketservice.service.interf.APIClient;
import com.revendedor.ticketservice.service.interf.TicketService;
import com.revendedor.ticketservice.util.exceptions.CustomException;
import com.revendedor.ticketservice.util.utils.ConvertFormat;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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
    @CircuitBreaker(name= "${spring.application.name}", fallbackMethod = "defaultFindById")
    public Optional<TicketDto> findById(int id) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);

        if(ticketOptional.isEmpty()){
            return Optional.empty();
        }

        Ticket ticket = ticketOptional.get();

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
        return Optional.of(ticketDto);
    }

    @Override
    public TicketDto save(CreateTicketRequest ctr) {
        if(ticketRepository.findTicketByCode(ctr.getCode()).isPresent()){
            throw new CustomException("Error");
        }

        Ticket ticket = new Ticket();
        ticket.setCode(ctr.getCode());
        ticket.setUserId(ctr.getUserId());
        ticket.setCreatedAt(ConvertFormat.localDateToInstant(ctr.getCreatedAt()));

        Ticket newTicket = ticketRepository.save(ticket);

        return ticketMapper.fromEntityToDto(newTicket);
    }

    @Override
    public void deleteById(int id) {
        ticketRepository.deleteById(id);
    }

    public TicketDto defaultFindById(int id, Exception exception){
        Ticket ticket = ticketRepository.findById(id).get();
        TicketDto ticketDto = ticketMapper.fromEntityToDto(ticket);
        ticketDto.setUserName("ERROR TEST USER NAME");
        return ticketDto;
    }
}
