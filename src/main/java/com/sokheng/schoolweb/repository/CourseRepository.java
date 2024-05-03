package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
}
