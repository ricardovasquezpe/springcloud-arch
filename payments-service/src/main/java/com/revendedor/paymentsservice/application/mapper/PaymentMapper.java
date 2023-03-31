package com.revendedor.paymentsservice.application.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.revendedor.paymentsservice.application.dto.PaymentDto;
import com.revendedor.paymentsservice.repository.entity.Payment;

public class PaymentMapper {
    public static PaymentDto fromEntityToDto(Payment p){
        return DozerBeanMapperBuilder.buildDefault().map(p, PaymentDto.class);
    }
}
