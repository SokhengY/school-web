package com.sokheng.schoolweb.controller;

import com.sokheng.schoolweb.dto.registration_dto.customer_dto.CustomerDTO;
import com.sokheng.schoolweb.entity.CustomerEntity;
import com.sokheng.schoolweb.mapper.CustomerMapper;
import com.sokheng.schoolweb.service.interfaces.CustomerService;
import com.sokheng.schoolweb.utils.BaseDataList;
import com.sokheng.schoolweb.utils.BaseResponse;
import com.sokheng.schoolweb.utils.BaseResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1.0.0/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public BaseResponseList<List<CustomerDTO>> findAll(@RequestParam(value = "page", defaultValue = "${spring.pagination.default.page}") Integer page, @RequestParam(value = "size", defaultValue = "${spring.pagination.default.size}") Integer size){

        BaseDataList<CustomerEntity> list = customerService.findAll(page, size);
        return BaseResponseList.<List<CustomerDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .pagination(list.getPagination())
                .data(customerMapper.from(list.getData()))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public BaseResponse<CustomerDTO> findById(@PathVariable("id") Integer id){

        return BaseResponse.<CustomerDTO>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .data(customerMapper.from(customerService.findById(id)))
                .build();
    }
}
