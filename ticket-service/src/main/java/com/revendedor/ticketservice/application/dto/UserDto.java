package com.revendedor.ticketservice.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Data
public class UserDto implements Serializable {
    private Integer id;
    private String name;
    private Instant birthday;
}
