package com.revendedor.ticketservice.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class TicketDto implements Serializable {
    public int id;
    public String code;
    public int userId;
    public String userName;
    public Instant createdAt;
}
