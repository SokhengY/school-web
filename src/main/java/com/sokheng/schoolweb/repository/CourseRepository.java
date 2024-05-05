package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    @Query(value = "SELECT id FROM course WHERE promotion_id = :id", nativeQuery = true)
    List<Integer> findAllByPromotionId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE course SET promotion_id = null WHERE id = :id", nativeQuery = true)
    void deletePromotionId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE course SET is_deleted = true WHERE id = :id", nativeQuery = true)
    void updateStatusById(@Param("id") Integer id);

    boolean existsByIdAndIsDeletedFalse(@Param("id") Integer id);
}
