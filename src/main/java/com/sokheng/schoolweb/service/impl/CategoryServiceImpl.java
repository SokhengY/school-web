package com.sokheng.schoolweb.service.impl;

import com.sokheng.schoolweb.dto.category_dto.CategoryDTO;
import com.sokheng.schoolweb.utils.BaseDataList;
import com.sokheng.schoolweb.entity.CategoryEntity;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.repository.CategoryRepository;
import com.sokheng.schoolweb.service.interfaces.CategoryService;
import com.sokheng.schoolweb.utils.BasePagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryEntity update(Integer id, CategoryDTO dto) {

        CategoryEntity category = this.findById(id);
        category.setName(dto.getName());
        category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return categoryRepository.save(category);
    }

    @Override
    public void softDelete(Integer id) {

        this.existsByIdAndStatus(id);
        categoryRepository.updateStatusById(id);
    }

    @Override
    public BaseDataList<CategoryEntity> findAll(Integer page, Integer size) {

        if (page <= 0) page = 1;
        if (size <= 0) size = 10;
        Page<CategoryEntity> entityPage = categoryRepository.findAll(PageRequest.of(page-1, size));
        return BaseDataList.<CategoryEntity>builder()
                .pagination(new BasePagination(page, size, entityPage.getTotalElements(), entityPage.getTotalPages()))
                .data(entityPage.getContent())
                .build();
    }

    @Override
    public void existsByIdAndStatus(Integer id) {

        boolean exists = categoryRepository.existsByIdAndIsDeletedFalse(id);
        if (!exists) throw new NotFoundException("category not exists!");
    }

    @Override
    public CategoryEntity findById(Integer id) {

        this.existsByIdAndStatus(id);
        return categoryRepository.findById(id).orElse(new CategoryEntity());
    }

    @Override
    public CategoryEntity create(CategoryEntity entity) {

        return categoryRepository.save(entity);
    }
}
