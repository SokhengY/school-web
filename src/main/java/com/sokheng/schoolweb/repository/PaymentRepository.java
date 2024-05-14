package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.PaymentEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {

    boolean existsById(@Param("id") @NotNull Integer id);
}
