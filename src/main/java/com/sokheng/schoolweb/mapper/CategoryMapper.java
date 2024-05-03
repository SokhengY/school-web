package com.sokheng.schoolweb.mapper;

import com.sokheng.schoolweb.dto.CategoryDTO;
import com.sokheng.schoolweb.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryEntity from(CategoryDTO dto);

    CategoryDTO from(CategoryEntity entity);
}
