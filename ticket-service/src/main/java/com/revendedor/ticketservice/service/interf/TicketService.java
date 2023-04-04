package com.revendedor.ticketservice.service.interf;

import com.revendedor.ticketservice.api.request.CreateTicketRequest;
import com.revendedor.ticketservice.application.dto.TicketDto;
import com.revendedor.ticketservice.util.exceptions.CustomException;

import java.util.List;

public interface TicketService {
    List<TicketDto> findAll();
    TicketDto findById(int id);
    TicketDto save(CreateTicketRequest ctr);
    boolean deleteById(int id);
}
