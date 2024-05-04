package com.sokheng.schoolweb.service.interfaces;

import com.sokheng.schoolweb.utils.BaseDataList;
import com.sokheng.schoolweb.entity.CategoryEntity;

public interface CategoryService {

    void softDelete(Integer id);

    BaseDataList<CategoryEntity> findAll(Integer page, Integer size);

    void existsByIdAndStatus(Integer id);

    CategoryEntity findById(Integer id);

    CategoryEntity create(CategoryEntity entity);
}
