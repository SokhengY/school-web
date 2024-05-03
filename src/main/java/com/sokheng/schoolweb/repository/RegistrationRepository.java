package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Integer> {
}
