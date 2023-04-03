package com.revendedor.ticketservice.service;


import com.revendedor.ticketservice.application.dto.TicketDto;
import com.revendedor.ticketservice.application.mapper.TicketMapper;
import com.revendedor.ticketservice.repository.TicketRepository;
import com.revendedor.ticketservice.repository.entity.Ticket;
import com.revendedor.ticketservice.service.impl.TicketServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTests {
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private TicketMapper ticketMapper;
    @InjectMocks
    private TicketServiceImpl ticketService;

    private Ticket ticket;

    @BeforeEach
    public void setup(){
        ticket = new Ticket();
        ticket.setId(1);
        ticket.setCreatedAt(Instant.now());
        ticket.setCode("DDD");
        ticket.setUserId(3);
    }

    @Test
    public void givenTicketObject_whenSave_thenReturnTicketObject(){
        Mockito.when(ticketRepository.save(Mockito.any())).thenReturn(ticket);

        TicketDto dto = new TicketDto();
        dto.setCode("DDD");
        dto.setCreatedAt(Instant.now());
        dto.setUserId(3);
        TicketDto newTicketDto = ticketService.save(dto);

        Assertions.assertThat(newTicketDto).isNotNull();
    }
}
