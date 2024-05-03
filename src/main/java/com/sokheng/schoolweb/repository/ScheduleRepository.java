package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
}
