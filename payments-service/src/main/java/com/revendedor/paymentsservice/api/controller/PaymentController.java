package com.revendedor.paymentsservice.api.controller;

import com.revendedor.paymentsservice.api.request.PaymentCreateRequest;
import com.revendedor.paymentsservice.application.dto.PaymentDto;
import com.revendedor.paymentsservice.service.interf.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody PaymentCreateRequest paymentCreateRequest){
        PaymentDto dto = paymentService.create(paymentCreateRequest);
        return new ResponseEntity<PaymentDto>(dto, HttpStatus.OK);
    }
}
