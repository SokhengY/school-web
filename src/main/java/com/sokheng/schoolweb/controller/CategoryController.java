package com.sokheng.schoolweb.controller;

import com.sokheng.schoolweb.dto.CategoryDTO;
import com.sokheng.schoolweb.mapper.CategoryMapper;
import com.sokheng.schoolweb.service.interfaces.CategoryService;
import com.sokheng.schoolweb.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1.0.0/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BaseResponse<CategoryDTO> create(@RequestBody CategoryDTO dto){

        return BaseResponse.<CategoryDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("success")
                .data(categoryMapper.from(categoryService.create(categoryMapper.from(dto))))
                .build();
    }
}
