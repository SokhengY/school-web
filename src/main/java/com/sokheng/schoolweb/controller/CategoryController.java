package com.sokheng.schoolweb.controller;

import com.sokheng.schoolweb.utils.BaseDataList;
import com.sokheng.schoolweb.dto.category_dto.CategoryDTO;
import com.sokheng.schoolweb.entity.CategoryEntity;
import com.sokheng.schoolweb.mapper.CategoryMapper;
import com.sokheng.schoolweb.service.interfaces.CategoryService;
import com.sokheng.schoolweb.utils.BaseResponse;
import com.sokheng.schoolweb.utils.BaseResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1.0.0/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public BaseResponse<CategoryDTO> update(@PathVariable("id") Integer id, @RequestBody CategoryDTO dto){

        return BaseResponse.<CategoryDTO>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .data(categoryMapper.from(categoryService.update(id, dto)))
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void softDelete(@PathVariable("id") Integer id){

        categoryService.softDelete(id);
        log.debug("CategoryController::softDelete "+ id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public BaseResponseList<List<CategoryDTO>> findAll(@RequestParam(value = "page", defaultValue = "${spring.pagination.default.page}") Integer page, @RequestParam(value = "size", defaultValue = "${spring.pagination.default.size}") Integer size){

        BaseDataList<CategoryEntity> entities = categoryService.findAll(page, size);
        return BaseResponseList.<List<CategoryDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .pagination(entities.getPagination())
                .data(categoryMapper.from(entities.getData()))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public BaseResponse<CategoryDTO> findById(@PathVariable("id") Integer id){

        return BaseResponse.<CategoryDTO>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .data(categoryMapper.from(categoryService.findById(id)))
                .build();
    }

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
