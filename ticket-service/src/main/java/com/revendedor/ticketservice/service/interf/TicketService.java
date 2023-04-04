package com.revendedor.ticketservice.service.interf;

import com.revendedor.ticketservice.api.request.CreateTicketRequest;
import com.revendedor.ticketservice.application.dto.TicketDto;
import com.revendedor.ticketservice.util.exceptions.CustomException;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<TicketDto> findAll();
    Optional<TicketDto> findById(int id);
    TicketDto save(CreateTicketRequest ctr);
    void deleteById(int id);
}
