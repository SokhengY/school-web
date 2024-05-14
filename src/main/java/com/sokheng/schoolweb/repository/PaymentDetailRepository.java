package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.PaymentDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetailEntity, Integer> {

    @Query(value = "SELECT * FROM payment_detail WHERE payment_id = :id ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<PaymentDetailEntity> findByPaymentId(@Param("id") Integer id);
}
