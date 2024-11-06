package com.springboot.payment_service.service.impl;

import com.springboot.payment_service.entity.Payments;
import com.springboot.payment_service.repository.PaymentRepository;
import com.springboot.payment_service.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Value("${stripe.api-key}")
    private String stripeApiKey;

    @Override
    public Payments processPayment(Double amount, String currency) {
        Stripe.apiKey = stripeApiKey;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("amount", (int)(amount*100));
            params.put("currency", currency);
            params.put("source", "tok_visa");
            Charge charge = Charge.create(params);
            Payments payments = new Payments();
            payments.setTransactionId(charge.getId());
            payments.setAmount(amount);
            payments.setCurrency(currency);
            payments.setPaymentStatus(charge.getStatus());
            payments.setPaymentDate(new Date());

            return paymentRepository.save(payments);

        } catch (StripeException e) {
            throw new RuntimeException("Payment failed", e);
        }
    }
}
