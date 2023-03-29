package com.revendedor.ticketservice.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Table(name = "tickets")
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "code", length = 45)
    private String code;
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "createdAt")
    private Instant createdAt;
}
