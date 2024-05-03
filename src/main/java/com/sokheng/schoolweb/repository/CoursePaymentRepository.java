package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.CoursePaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursePaymentRepository extends JpaRepository<CoursePaymentEntity, Integer> {
}
