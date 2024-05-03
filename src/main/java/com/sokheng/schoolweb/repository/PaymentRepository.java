package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
}
