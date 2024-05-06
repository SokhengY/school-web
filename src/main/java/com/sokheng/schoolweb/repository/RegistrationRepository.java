package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Integer> {

    @Query(value = "SELECT EXISTS(SELECT 1 FROM registration WHERE customer_id = :customerId AND course_id = :courseId)", nativeQuery = true)
    boolean existsByCustomerIdAndCourseId(@Param("customerId") Integer customerId, @Param("courseId") Integer courseId);
}
