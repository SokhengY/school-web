package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.PaymentDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetailEntity, Integer> {
}
