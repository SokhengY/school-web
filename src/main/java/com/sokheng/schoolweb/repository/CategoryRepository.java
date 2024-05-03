package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
