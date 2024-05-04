package com.sokheng.schoolweb.controller;

import com.sokheng.schoolweb.dto.course_dto.price_dto.PriceDTO;
import com.sokheng.schoolweb.dto.course_dto.price_dto.PriceRequest;
import com.sokheng.schoolweb.mapper.PriceMapper;
import com.sokheng.schoolweb.service.interfaces.PriceService;
import com.sokheng.schoolweb.utils.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1.0.0/price")
public class PriceController {

    private final PriceService priceService;
    private final PriceMapper priceMapper;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public BaseResponse<PriceDTO> updatePrice(@PathVariable("id") Integer id, @RequestBody @Valid PriceRequest request){

        return BaseResponse.<PriceDTO>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .data(priceMapper.from(priceService.updatePrice(id, request)))
                .build();
    }
}
