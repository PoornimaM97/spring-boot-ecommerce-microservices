package com.springboot.payment_service.service;

import com.springboot.payment_service.entity.Payments;

public interface PaymentService {

    Payments processPayment(Double amount, String currency);
}
