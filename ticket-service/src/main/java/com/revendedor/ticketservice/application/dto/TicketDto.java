package com.revendedor.ticketservice.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;

@Data
public class TicketDto implements Serializable {
    public int id;
    public String code;
    public int userId;
    public String userName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public Instant createdAt;
}
