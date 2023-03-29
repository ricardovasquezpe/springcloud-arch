package com.revendedor.userservice.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

@Table(name = "users")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "birthday")
    private Instant birthday;
}
