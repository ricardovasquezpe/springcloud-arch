package com.revendedor.ticketservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revendedor.ticketservice.api.request.CreateTicketRequest;
import com.revendedor.ticketservice.application.dto.TicketDto;
import com.revendedor.ticketservice.service.interf.TicketService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebMvcTest
public class TicketControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Unit test for create ticket endpoint")
    @Test
    public void givenTicketObject_whenSave_thenReturnTicketObjectCreated() throws Exception{
        CreateTicketRequest ctr = new CreateTicketRequest();
        ctr.setUserId(1);
        ctr.setCode("HHHH");
        ctr.setCreatedAt(LocalDate.now());

        TicketDto dto = new TicketDto();
        dto.setId(1);
        dto.setCode("HHH");
        dto.setCreatedAt(Instant.now());

        BDDMockito.given(ticketService.save(Mockito.any())).willReturn(dto);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ctr)));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(1)));
    }

    @DisplayName("UnitTest for findAll endpoint")
    @Test
    public void givenTicketList_whenFindAll_thenReturnTicketList() throws Exception{
        TicketDto dto = new TicketDto();
        dto.setId(1);
        dto.setCode("HHH");
        dto.setCreatedAt(Instant.now());

        List<TicketDto> ticketList = List.of(dto, dto, dto);
        BDDMockito.given(ticketService.findAll()).willReturn(ticketList);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/findAll")
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(ticketList.size())));
    }

    @DisplayName("UnitTest for findById endpoint - Positive scenario")
    @Test
    public void givenTicketId_whenFindById_thenReturnTicketObject() throws Exception {
        TicketDto dto = new TicketDto();
        dto.setId(1);
        dto.setCode("HHH");
        dto.setCreatedAt(Instant.now());

        BDDMockito.given(ticketService.findById(1)).willReturn(Optional.of(dto));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/findById/{id}", dto.getId())
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(dto.getId())));
    }

    @DisplayName("UnitTest for findById endpoint - Negative scenario")
    @Test
    public void givenInvalidTicketId_whenFindById_thenReturnEmpty() throws Exception {
        BDDMockito.given(ticketService.findById(1)).willReturn(Optional.empty());

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/findById/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @DisplayName("UnitTest for delete by ID endpoint")
    @Test
    public void givenTicketId_whenDelete_thenReturn200() throws Exception {
        int ticketId = 1;
        BDDMockito.willDoNothing().given(ticketService).deleteById(ticketId);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/delete/{id}", ticketId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
