package com.revendedor.ticketservice.service;


import com.revendedor.ticketservice.application.dto.TicketDto;
import com.revendedor.ticketservice.application.mapper.TicketMapper;
import com.revendedor.ticketservice.repository.TicketRepository;
import com.revendedor.ticketservice.repository.entity.Ticket;
import com.revendedor.ticketservice.service.impl.TicketServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    private TicketDto ticketDto;

    @BeforeEach
    public void setup(){
        ticket = new Ticket();
        ticket.setId(1);
        ticket.setCreatedAt(Instant.now());
        ticket.setCode("DDD");
        ticket.setUserId(3);

        ticketDto = new TicketDto();
        ticketDto.setCode("DDD");
        ticketDto.setCreatedAt(Instant.now());
        ticketDto.setUserId(3);

        TicketDto ticketDtoMapped = new TicketDto();
        ticketDto.setCode("DDD");
        ticketDto.setCreatedAt(Instant.now());
        ticketDto.setUserId(3);
        ticketDto.setId(1);


        Mockito.when(ticketMapper.fromEntityToDto(Mockito.any())).thenReturn(ticketDtoMapped);
    }

    @DisplayName("Unit Test for Save ticket method")
    @Test
    public void givenTicketObject_whenSave_thenReturnTicketObject(){
        Mockito.when(ticketRepository.save(Mockito.any())).thenReturn(ticket);

        TicketDto newTicketDto = ticketService.save(ticketDto);

        Assertions.assertThat(newTicketDto).isNotNull();
    }
}
