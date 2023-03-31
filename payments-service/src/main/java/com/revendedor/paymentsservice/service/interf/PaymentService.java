package com.revendedor.paymentsservice.service.interf;

import com.revendedor.paymentsservice.api.request.PaymentCreateRequest;
import com.revendedor.paymentsservice.application.dto.PaymentDto;

public interface PaymentService {
    PaymentDto create(PaymentCreateRequest r);
}
