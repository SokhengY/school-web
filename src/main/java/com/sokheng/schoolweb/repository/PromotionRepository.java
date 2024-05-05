package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PromotionRepository extends JpaRepository<PromotionEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE promotion SET is_deleted = true WHERE id = :id", nativeQuery = true)
    void updateStatusById(@Param("id") Integer id);

    boolean existsByIdAndIsDeletedFalse(@Param("id") Integer id);
}
