package com.revendedor.ticketservice.repository;

import com.revendedor.ticketservice.repository.entity.Ticket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TicketRepositoryIT {

    @Autowired
    TicketRepository ticketRepository;

    Ticket ticket;

    @BeforeEach
    public void setup(){
        ticket = new Ticket();
        ticket.setUserId(2);
        ticket.setCode("BBB");
        ticket.setCreatedAt(Instant.now());
    }

    @DisplayName("UnitTest for save a new ticket")
    @Test
    public void givenTicketData_whenSave_thenReturnSavedTicket(){
        Ticket newTicket = ticketRepository.save(ticket);

        Assertions.assertThat(newTicket).isNotNull();
        Assertions.assertThat(newTicket.getId()).isGreaterThan(0);
    }

    @DisplayName("UnitTest for findAll tickets")
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

    @DisplayName("Unit Test for find a ticket by ID")
    @Test
    public void givenTicketData_whenFindById_thenReturnTicketData(){
        Ticket newTicket = ticketRepository.save(ticket);

        Ticket ticketDB = ticketRepository.findById(newTicket.getId()).get();

        Assertions.assertThat(ticketDB).isNotNull();
    }

    @DisplayName("Unit Test for update ticket data")
    @Test
    public void givenTicketData_whenUpdateTicket_thenReturnUpdatedTicket(){
        Ticket newTicket = ticketRepository.save(ticket);

        newTicket.setCode("CCC");
        Ticket newTicketUpdated = ticketRepository.save(newTicket);

        Assertions.assertThat(newTicketUpdated).isNotNull();
        Assertions.assertThat(newTicketUpdated.getCode()).isEqualTo(newTicket.getCode());
    }

    @DisplayName("Unit Test for delete a ticket by ID")
    @Test
    public void given_when_then(){
        Ticket newTicket = ticketRepository.save(ticket);

        ticketRepository.deleteById(newTicket.getId());
        Optional<Ticket> findTicket = ticketRepository.findById(newTicket.getId());

        Assertions.assertThat(findTicket).isEmpty();
    }
}
