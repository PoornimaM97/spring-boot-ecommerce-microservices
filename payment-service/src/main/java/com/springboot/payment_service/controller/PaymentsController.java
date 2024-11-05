package com.springboot.payment_service.controller;

import com.springboot.payment_service.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsController {

    private PaymentService paymentService;


}
