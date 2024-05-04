package com.sokheng.schoolweb.service.interfaces;

import com.sokheng.schoolweb.dto.CourseDTO.CourseDTO;
import com.sokheng.schoolweb.entity.CourseEntity;
import com.sokheng.schoolweb.utils.BaseDataList;

public interface CourseService {

    void deleteById(Integer id);

    BaseDataList<CourseEntity> findAll(Integer page, Integer size);

    CourseEntity findById(Integer id);

    void existsByIdAndStatus(Integer id);

    CourseEntity create(CourseDTO dto);
}
