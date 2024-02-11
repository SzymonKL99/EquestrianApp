package com.crud.equestrianapp.repository;

import com.crud.equestrianapp.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
