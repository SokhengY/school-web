package com.sokheng.schoolweb.service.impl;

import com.sokheng.schoolweb.dto.CourseDTO.CourseDTO;
import com.sokheng.schoolweb.entity.CategoryEntity;
import com.sokheng.schoolweb.entity.CourseEntity;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.mapper.CourseMapper;
import com.sokheng.schoolweb.repository.CourseRepository;
import com.sokheng.schoolweb.service.interfaces.CategoryService;
import com.sokheng.schoolweb.service.interfaces.CourseService;
import com.sokheng.schoolweb.utils.BaseDataList;
import com.sokheng.schoolweb.utils.BasePagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryService categoryService;
    private final CourseMapper courseMapper;

    @Override
    public void deleteById(Integer id) {

        this.existsByIdAndStatus(id);
        courseRepository.updateStatusById(id);
    }

    @Override
    public BaseDataList<CourseEntity> findAll(Integer page, Integer size) {

        if (page <= 0) page = 1;
        if (size <= 0) size = 10;
        Page<CourseEntity> entities = courseRepository.findAll(PageRequest.of(page-1, size));
        return BaseDataList.<CourseEntity>builder()
                .pagination(new BasePagination(page, size, entities.getTotalElements(), entities.getTotalPages()))
                .data(entities.getContent())
                .build();
    }

    @Override
    public CourseEntity findById(Integer id) {

        this.existsByIdAndStatus(id);
        return courseRepository.findById(id).orElse(new CourseEntity());
    }

    @Override
    public void existsByIdAndStatus(Integer id) {

        boolean exists = courseRepository.existsByIdAndIsDeletedFalse(id);
        if (!exists) throw new NotFoundException("course not exists");
    }

    @Override
    public CourseEntity create(CourseDTO dto) {

        CategoryEntity category = categoryService.findById(dto.getCategory().getId());
        CourseEntity course = courseMapper.from(dto);
        course.setCategoryEntity(category);
        return courseRepository.save(course);
    }
}
