package com.revendedor.ticketservice.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CreateTicketRequest implements Serializable {
    public String code;
    public int userId;
    @JsonFormat(pattern="dd/MM/yyyy")
    public LocalDate createdAt;
}
