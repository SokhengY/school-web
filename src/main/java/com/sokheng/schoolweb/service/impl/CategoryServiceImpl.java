package com.sokheng.schoolweb.service.impl;

import com.sokheng.schoolweb.entity.CategoryEntity;
import com.sokheng.schoolweb.repository.CategoryRepository;
import com.sokheng.schoolweb.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryEntity create(CategoryEntity entity) {

        return categoryRepository.save(entity);
    }
}
