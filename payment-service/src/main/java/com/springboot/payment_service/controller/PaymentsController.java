package com.springboot.payment_service.controller;

import com.springboot.payment_service.dto.PaymentsDto;
import com.springboot.payment_service.entity.Payments;
import com.springboot.payment_service.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsController {

    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payments> addPayment(@RequestBody PaymentsDto paymentsDto){
        Payments payments = paymentService.processPayment(paymentsDto.getAmount(), paymentsDto.getCurrency());
        return new ResponseEntity<>(payments, HttpStatus.CREATED);
    }

    public ResponseEntity<Payments> getPayment(String transactionId){
        return paymentService.getPaymentDetails(transactionId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
