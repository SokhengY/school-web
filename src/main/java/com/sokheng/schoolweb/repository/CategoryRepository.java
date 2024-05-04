package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE category SET is_deleted = true WHERE id = :id", nativeQuery = true)
    void updateStatusById(@Param("id") Integer id);

    boolean existsByIdAndIsDeletedFalse(@Param("id") Integer id);

}
