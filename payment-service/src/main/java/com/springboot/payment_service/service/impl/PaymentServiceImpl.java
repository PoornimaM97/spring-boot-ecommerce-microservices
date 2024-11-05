package com.springboot.payment_service.service.impl;

import com.springboot.payment_service.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PaymentServiceImpl {

    private PaymentRepository paymentRepository;
}
