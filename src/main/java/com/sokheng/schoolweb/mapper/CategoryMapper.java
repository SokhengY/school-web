package com.sokheng.schoolweb.mapper;

import com.sokheng.schoolweb.dto.CategoryDTO.CategoryDTO;
import com.sokheng.schoolweb.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    List<CategoryDTO> from(List<CategoryEntity> list);

    CategoryEntity from(CategoryDTO dto);

    CategoryDTO from(CategoryEntity entity);
}
