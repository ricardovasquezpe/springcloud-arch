package com.revendedor.ticketservice.service.impl;

import com.revendedor.ticketservice.application.dto.TicketDto;
import com.revendedor.ticketservice.application.dto.UserDto;
import com.revendedor.ticketservice.application.mapper.TicketMapper;
import com.revendedor.ticketservice.repository.TicketRepository;
import com.revendedor.ticketservice.repository.entity.Ticket;
import com.revendedor.ticketservice.service.interf.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketMapper ticketMapper;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<TicketDto> findAll() {
       return ticketRepository.findAll().stream().map(ticketMapper::fromEntityToDto).collect(Collectors.toList());
    }

    @Override
    public TicketDto findById(int id) {
        Ticket ticket = ticketRepository.findById(id).get();

        ResponseEntity<UserDto> response =
                restTemplate.getForEntity("http://localhost:8080/findById/" + ticket.getUserId(), UserDto.class);

        UserDto dto = response.getBody();
        TicketDto ticketDto = ticketMapper.fromEntityToDto(ticket);
        ticketDto.setUserName(dto.getName());
        return ticketDto;
    }
}
