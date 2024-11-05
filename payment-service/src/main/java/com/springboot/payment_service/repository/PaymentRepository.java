package com.springboot.payment_service.repository;

import com.springboot.payment_service.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payments, Long> {
}
