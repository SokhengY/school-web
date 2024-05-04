package com.sokheng.schoolweb.controller;

import com.sokheng.schoolweb.dto.CourseDTO.CourseDTO;
import com.sokheng.schoolweb.dto.CourseDTO.CourseRequest;
import com.sokheng.schoolweb.entity.CourseEntity;
import com.sokheng.schoolweb.mapper.CourseMapper;
import com.sokheng.schoolweb.service.interfaces.CourseService;
import com.sokheng.schoolweb.utils.BaseDataList;
import com.sokheng.schoolweb.utils.BaseResponse;
import com.sokheng.schoolweb.utils.BaseResponseList;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1.0.0/course")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{id}")
    public void deleteById(@PathVariable("id") Integer id){

        courseService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public BaseResponseList<List<CourseDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "${spring.pagination.default.page}") Integer page,
            @RequestParam(value = "size", defaultValue = "${spring.pagination.default.size}") Integer size
    ){

        BaseDataList<CourseEntity> list = courseService.findAll(page, size);
        return BaseResponseList.<List<CourseDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .pagination(list.getPagination())
                .data(courseMapper.from(list.getData()))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public BaseResponse<CourseDTO> findById(@PathVariable("id") Integer id){

        return BaseResponse.<CourseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .data(courseMapper.from(courseService.findById(id)))
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping
    public BaseResponse<CourseDTO> create(@RequestBody @Valid CourseRequest request){

        return BaseResponse.<CourseDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("success")
                .data(courseMapper.from(courseService.create(courseMapper.from(request))))
                .build();
    }
}
