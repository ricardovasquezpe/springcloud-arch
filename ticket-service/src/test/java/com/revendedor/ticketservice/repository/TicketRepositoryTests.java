package com.revendedor.ticketservice.repository;

import com.revendedor.ticketservice.repository.entity.Ticket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.List;

@DataJpaTest
public class TicketRepositoryTests {

    @Autowired
    TicketRepository ticketRepository;

    @DisplayName("UnitTest for save a new ticket")
    @Test
    public void givenTicketData_whenSave_thenReturnSavedTicket(){
        Ticket ticket = new Ticket();
        ticket.setUserId(2);
        ticket.setCode("BBB");
        ticket.setCreatedAt(Instant.now());

        Ticket newTicket = ticketRepository.save(ticket);

        Assertions.assertThat(newTicket).isNotNull();
        Assertions.assertThat(newTicket.getId()).isGreaterThan(0);
    }

    @DisplayName("UnitTest for findAll method")
    @Test
    public void givenTicketList_whenFindAll_thenReturnList(){
        Ticket ticket1 = new Ticket();
        ticket1.setUserId(2);
        ticket1.setCode("BBB");
        ticket1.setCreatedAt(Instant.now());

        Ticket ticket2 = new Ticket();
        ticket2.setUserId(2);
        ticket2.setCode("BBB");
        ticket2.setCreatedAt(Instant.now());

        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);

        List<Ticket> ticketList = ticketRepository.findAll();

        Assertions.assertThat(ticketList).isNotNull();
        Assertions.assertThat(ticketList.size()).isEqualTo(2);
    }
}
