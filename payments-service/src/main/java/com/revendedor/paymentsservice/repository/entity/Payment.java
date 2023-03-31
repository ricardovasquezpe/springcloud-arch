package com.revendedor.paymentsservice.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

@Table(name = "payments")
@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ticketId")
    private Integer ticketId;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;
}
