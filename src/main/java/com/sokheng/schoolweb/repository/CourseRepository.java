package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE course SET is_deleted = true WHERE id = :id", nativeQuery = true)
    void updateStatusById(@Param("id") Integer id);

    boolean existsByIdAndIsDeletedFalse(@Param("id") Integer id);
}
