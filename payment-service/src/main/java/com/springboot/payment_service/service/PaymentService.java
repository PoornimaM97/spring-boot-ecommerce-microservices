package com.springboot.payment_service.service;

import aj.org.objectweb.asm.commons.Remapper;
import com.springboot.payment_service.entity.Payments;

import java.util.Optional;

public interface PaymentService {

    Payments processPayment(Double amount, String currency);

    Optional<Payments> getPaymentDetails(String transactionId);
}
