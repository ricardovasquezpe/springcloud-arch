package com.revendedor.ticketservice.service;

import com.revendedor.basedomains.application.dto.UserDto;
import com.revendedor.ticketservice.api.request.CreateTicketRequest;
import com.revendedor.ticketservice.application.dto.TicketDto;
import com.revendedor.ticketservice.application.mapper.TicketMapper;
import com.revendedor.ticketservice.repository.TicketRepository;
import com.revendedor.ticketservice.repository.entity.Ticket;
import com.revendedor.ticketservice.service.impl.TicketServiceImpl;
import com.revendedor.ticketservice.service.interf.APIClient;
import com.revendedor.ticketservice.util.exceptions.CustomException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTests {
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private TicketMapper ticketMapper;
    @Mock
    private APIClient apiClient;
    @InjectMocks
    private TicketServiceImpl ticketService;

    private Ticket ticket;
    private CreateTicketRequest createTicketRequest;
    private TicketDto ticketDto;
    private UserDto userDto;

    @BeforeEach
    public void setup(){
        ticket = new Ticket();
        ticket.setId(1);
        ticket.setCreatedAt(Instant.now());
        ticket.setCode("DDD");
        ticket.setUserId(3);

        createTicketRequest = new CreateTicketRequest();
        createTicketRequest.setCode("DDDD");
        createTicketRequest.setCreatedAt(LocalDate.now());
        createTicketRequest.setUserId(3);

        ticketDto = new TicketDto();
        ticketDto.setCode("DDD");
        ticketDto.setCreatedAt(Instant.now());
        ticketDto.setUserId(3);
        ticketDto.setId(1);

        userDto = new UserDto();
        userDto.setName("Ricardo");
        userDto.setId(2);
        userDto.setBirthday(Instant.now());
    }

    @DisplayName("Unit Test for Save ticket method")
    @Test
    public void givenTicketObject_whenSave_thenReturnTicketObject(){
        //Mockito.when(ticketRepository.save(Mockito.any())).thenReturn(ticket);
        BDDMockito.given(ticketRepository.findTicketByCode(Mockito.any())).willReturn(Optional.empty());
        BDDMockito.given(ticketRepository.save(Mockito.any())).willReturn(ticket);
        BDDMockito.given(ticketMapper.fromEntityToDto(Mockito.any())).willReturn(ticketDto);

        TicketDto newTicketDto = ticketService.save(createTicketRequest);

        Assertions.assertThat(newTicketDto).isNotNull();
    }

    @DisplayName("Unit Test for Save ticket method which throws exception")
    @Test
    public void givenExistingCode_whenSave_thenThrowsException(){
        //Mockito.when(ticketRepository.save(Mockito.any())).thenReturn(ticket);
        BDDMockito.given(ticketRepository.findTicketByCode(Mockito.any())).willReturn(Optional.of(ticket));

        org.junit.jupiter.api.Assertions.assertThrows(CustomException.class, () -> {
           ticketService.save(createTicketRequest);
        });

        Mockito.verify(ticketRepository, Mockito.never()).save(Mockito.any(Ticket.class));
    }

    @DisplayName("Unit test for findAll tickets")
    @Test
    public void givenTicketList_whenFindAll_thenReturnTicketList(){
        BDDMockito.given(ticketMapper.fromEntityToDto(Mockito.any())).willReturn(ticketDto);
        BDDMockito.given(ticketRepository.findAll()).willReturn(List.of(ticket, ticket));

        List<TicketDto> ticketList = ticketService.findAll();

        Assertions.assertThat(ticketList.size()).isEqualTo(2);
    }

    @DisplayName("Unit test for findById method")
    @Test
    public void givenTicketId_whenFindById_thenReturnTicketObject(){
        BDDMockito.given(ticketRepository.findById(Mockito.any())).willReturn(Optional.of(ticket));
        BDDMockito.given(apiClient.findById(Mockito.anyInt())).willReturn(userDto);
        BDDMockito.given(ticketMapper.fromEntityToDto(Mockito.any())).willReturn(ticketDto);

        TicketDto ticketFind = ticketService.findById(ticket.getId());

        Assertions.assertThat(ticketFind).isNotNull();
        Assertions.assertThat(ticketFind.getId()).isEqualTo(ticket.getId());
    }

    @DisplayName("Unit test for deleteById method")
    @Test
    public void givenTicketId_whenDeleteById_thenDeleteTicketObject(){
        int ticketId = 1;
        BDDMockito.willDoNothing().given(ticketRepository).deleteById(ticketId);

        ticketService.deleteById(ticketId);

        Mockito.verify(ticketRepository, Mockito.times(1)).deleteById(ticketId);
    }
}
