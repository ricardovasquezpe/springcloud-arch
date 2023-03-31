package com.revendedor.paymentsservice.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PaymentCreateRequest implements Serializable {
    @NotNull
    Integer ticketId;
    @NotNull
    Integer userId;
    @NotNull
    BigDecimal price;
}
