package com.sokheng.schoolweb.mapper;

import com.sokheng.schoolweb.dto.course_dto.CourseDTO;
import com.sokheng.schoolweb.dto.course_dto.CourseRequest;
import com.sokheng.schoolweb.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    List<CourseDTO> from(List<CourseEntity> list);

    @Mapping(source = "price", target = "priceEntities")
    CourseEntity from(CourseDTO dto);

    @Mapping(source = "categoryEntity", target = "category")
    @Mapping(source = "priceEntities", target = "price")
    CourseDTO from(CourseEntity entity);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "price", target = "price")
    CourseDTO from(CourseRequest request);

}
