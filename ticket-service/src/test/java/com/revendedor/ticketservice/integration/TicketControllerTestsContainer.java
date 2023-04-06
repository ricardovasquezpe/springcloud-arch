package com.revendedor.ticketservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revendedor.ticketservice.api.request.CreateTicketRequest;
import com.revendedor.ticketservice.repository.TicketRepository;
import com.revendedor.ticketservice.repository.entity.Ticket;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TicketControllerTestsContainer extends AbstractionBaseTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        ticketRepository.deleteAll();
    }

    @DisplayName("Integration test for create ticket endpoint")
    @Test
    public void givenTicketObject_whenSave_thenReturnTicketObjectCreated() throws Exception{
        CreateTicketRequest ctr = new CreateTicketRequest();
        ctr.setUserId(1);
        ctr.setCode("HHHH");
        ctr.setCreatedAt(LocalDate.now());

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ctr)));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()));
    }

    @DisplayName("Integration test for findAll endpoint")
    @Test
    public void givenTicketList_whenFindAll_thenReturnTicketList() throws Exception{
        Ticket ticket = new Ticket();
        ticket.setCode("HHH");
        ticket.setCreatedAt(Instant.now());
        ticket.setUserId(1);

        Ticket ticket2 = new Ticket();
        ticket2.setCode("DDD");
        ticket2.setCreatedAt(Instant.now());
        ticket2.setUserId(2);

        List<Ticket> ticketList = List.of(ticket, ticket2);
        ticketRepository.saveAll(ticketList);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/findAll")
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(ticketList.size())));
    }

    @DisplayName("IntegrationTest for findById endpoint - Positive scenario")
    @Test
    public void givenTicketId_whenFindById_thenReturnTicketObject() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setUserId(1);
        ticket.setCode("HHH");
        ticket.setCreatedAt(Instant.now());

        ticketRepository.save(ticket);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/findById/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(1)));
    }
}
