package com.revendedor.paymentsservice.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Data
public class PaymentDto implements Serializable {
    Integer id;
    Integer ticketId;
    Integer userId;
    BigDecimal price;
}
