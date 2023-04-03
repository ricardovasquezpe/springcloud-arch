package com.revendedor.ticketservice.service.interf;

import com.revendedor.ticketservice.application.dto.TicketDto;

import java.util.List;

public interface TicketService {
    List<TicketDto> findAll();
    TicketDto findById(int id);

    TicketDto save(TicketDto dto);
}
