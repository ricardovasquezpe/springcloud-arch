package com.revendedor.userservice.api.controller;

import com.revendedor.basedomains.application.events.PaymentEvent;
import com.revendedor.userservice.application.dto.UserDto;
import com.revendedor.userservice.service.interf.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping
//@Slf4j
public class UsersController {
    Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    UsersService usersService;

    @Value("${spring.datasource.url}")
    private String gitProperty;

    /*@Value("${config.test}")
    private String vaultProperty;*/

    @Value("${server.port}")
    private String port;

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDto>> findAll(){
        LocalDateTime localDateTime = LocalDateTime.now();
        //log.info("This Logs page findAll " + localDateTime);
        return new ResponseEntity<List<UserDto>>(usersService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable("userId") int userId){
        LocalDateTime localDateTime = LocalDateTime.now();
        //log.info("This Logs page findById " + localDateTime);

        UserDto dto = usersService.findById(userId);
        return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestHeader(value = "id") String userId,
                                       @RequestHeader(value = "role") String role){
        return new ResponseEntity<String>(userId + " - " + role, HttpStatus.OK);
    }

    @GetMapping("/which-port")
    public ResponseEntity<String> whichPort(){
        return new ResponseEntity<String>("The port: " + port, HttpStatus.OK);
    }

    @KafkaListener(topics = "paymentTopic", groupId = "mygroup")
    public void newPaymentConsumer(PaymentEvent payment){
        logger.info("Payment Ticket Id: " + payment.getTicketId());
        logger.info("Payment User Id: " + payment.getUserId());
        logger.info("Payment Price: " + payment.getPrice());
    }
}
