package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
}
