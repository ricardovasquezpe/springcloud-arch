package com.revendedor.ticketservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revendedor.ticketservice.api.request.CreateTicketRequest;
import com.revendedor.ticketservice.application.dto.TicketDto;
import com.revendedor.ticketservice.repository.TicketRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TicketControllerTests {
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

    @DisplayName("Unit test for create ticket endpoint")
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
}
