package com.revendedor.ticketservice.application.mapper;

import com.revendedor.ticketservice.application.dto.TicketDto;
import com.revendedor.ticketservice.repository.entity.Ticket;
import org.springframework.stereotype.Component;
import com.github.dozermapper.core.DozerBeanMapperBuilder;

@Component
public class TicketMapper {
    public TicketDto fromEntityToDto(Ticket t){
        return DozerBeanMapperBuilder.buildDefault().map(t, TicketDto.class);
    }
}
