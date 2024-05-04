package com.sokheng.schoolweb.mapper;

import com.sokheng.schoolweb.dto.CourseDTO.CourseDTO;
import com.sokheng.schoolweb.dto.CourseDTO.CourseRequest;
import com.sokheng.schoolweb.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    List<CourseDTO> from(List<CourseEntity> list);

    CourseEntity from(CourseDTO dto);

    @Mapping(source = "categoryEntity", target = "category")
    CourseDTO from(CourseEntity entity);

    @Mapping(source = "categoryId", target = "category.id")
    CourseDTO from(CourseRequest request);

}
