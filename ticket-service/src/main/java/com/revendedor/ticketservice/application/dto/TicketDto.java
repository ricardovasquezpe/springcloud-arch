package com.revendedor.ticketservice.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class TicketDto implements Serializable {
    private Integer id;
    private String code;
    private Instant createdAt;
}
