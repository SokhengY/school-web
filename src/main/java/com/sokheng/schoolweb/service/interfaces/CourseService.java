package com.sokheng.schoolweb.service.interfaces;

import com.sokheng.schoolweb.dto.course_dto.CourseDTO;
import com.sokheng.schoolweb.dto.course_dto.CourseRequest;
import com.sokheng.schoolweb.dto.course_dto.price_dto.PriceRequest;
import com.sokheng.schoolweb.entity.CourseEntity;
import com.sokheng.schoolweb.entity.PriceEntity;
import com.sokheng.schoolweb.utils.BaseDataList;

public interface CourseService {

    CourseEntity update(Integer id, CourseDTO dto);

    void deleteById(Integer id);

    BaseDataList<CourseEntity> findAll(Integer page, Integer size);

    CourseEntity findById(Integer id);

    void existsByIdAndStatus(Integer id);

    CourseEntity create(CourseDTO dto);
}
