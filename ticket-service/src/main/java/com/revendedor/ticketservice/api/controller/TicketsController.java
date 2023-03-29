package com.revendedor.ticketservice.api.controller;

import com.revendedor.ticketservice.application.dto.TicketDto;
import com.revendedor.ticketservice.service.interf.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class TicketsController {

    @Autowired
    TicketService ticketService;

    @GetMapping("findAll")
    public ResponseEntity<List<TicketDto>> findAll(){
        return new ResponseEntity<List<TicketDto>>(ticketService.findAll(), HttpStatus.OK);
    }
}
