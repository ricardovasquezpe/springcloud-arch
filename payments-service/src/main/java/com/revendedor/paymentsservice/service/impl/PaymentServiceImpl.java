package com.revendedor.paymentsservice.service.impl;

import com.revendedor.paymentsservice.api.request.PaymentCreateRequest;
import com.revendedor.paymentsservice.application.dto.PaymentDto;
import com.revendedor.paymentsservice.application.mapper.PaymentMapper;
import com.revendedor.paymentsservice.repository.PaymentRepository;
import com.revendedor.paymentsservice.repository.entity.Payment;
import com.revendedor.paymentsservice.service.interf.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public PaymentDto create(PaymentCreateRequest r) {
        Payment p = new Payment();
        p.setUserId(r.getUserId());
        p.setTicketId(r.getTicketId());
        p.setPrice(r.getPrice());

        Payment newPayment = paymentRepository.save(p);
        return PaymentMapper.fromEntityToDto(newPayment);
    }
}
