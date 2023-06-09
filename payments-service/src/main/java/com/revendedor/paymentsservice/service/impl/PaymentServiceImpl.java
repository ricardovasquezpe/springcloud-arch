package com.revendedor.paymentsservice.service.impl;

import com.revendedor.basedomains.application.events.PaymentEvent;
import com.revendedor.paymentsservice.api.request.PaymentCreateRequest;
import com.revendedor.paymentsservice.application.dto.PaymentDto;
import com.revendedor.paymentsservice.application.mapper.PaymentMapper;
import com.revendedor.paymentsservice.repository.PaymentRepository;
import com.revendedor.paymentsservice.repository.entity.Payment;
import com.revendedor.paymentsservice.service.interf.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @Override
    public PaymentDto create(PaymentCreateRequest r) {
        Payment p = new Payment();
        p.setUserId(r.getUserId());
        p.setTicketId(r.getTicketId());
        p.setPrice(r.getPrice());

        Payment newPayment = paymentRepository.save(p);

        PaymentEvent event = new PaymentEvent();
        event.setId(newPayment.getId());
        event.setUserId(newPayment.getUserId());
        event.setTicketId(newPayment.getTicketId());
        event.setPrice(newPayment.getPrice());

        Message<PaymentEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "paymentTopic")
                .build();
        kafkaTemplate.send(message);

        return PaymentMapper.fromEntityToDto(newPayment);
    }
}
