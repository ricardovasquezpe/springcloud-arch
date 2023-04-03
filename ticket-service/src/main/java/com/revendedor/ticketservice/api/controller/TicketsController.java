package com.revendedor.ticketservice.api.controller;

import com.revendedor.ticketservice.api.request.CreateTicketRequest;
import com.revendedor.ticketservice.application.dto.TicketDto;
import com.revendedor.ticketservice.service.interf.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TicketsController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/findAll")
    public ResponseEntity<List<TicketDto>> findAll(){
        return new ResponseEntity<List<TicketDto>>(ticketService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById/{ticketId}")
    public ResponseEntity<TicketDto> findById(@PathVariable("ticketId") int ticketId){
        TicketDto dto = ticketService.findById(ticketId);
        return new ResponseEntity<TicketDto>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TicketDto> create(@RequestBody CreateTicketRequest ctr){
        TicketDto dto = ticketService.save(ctr);
        return new ResponseEntity<TicketDto>(dto, HttpStatus.CREATED);
    }
}
