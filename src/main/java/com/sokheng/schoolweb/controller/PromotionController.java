package com.sokheng.schoolweb.controller;

import com.sokheng.schoolweb.dto.promotion_dto.PromotionDTO;
import com.sokheng.schoolweb.dto.promotion_dto.PromotionRequest;
import com.sokheng.schoolweb.entity.PromotionEntity;
import com.sokheng.schoolweb.mapper.PromotionMapper;
import com.sokheng.schoolweb.service.interfaces.PromotionService;
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
@RequestMapping("api/v1.0.0/promotion")
public class PromotionController {

    private final PromotionService promotionService;
    private final PromotionMapper promotionMapper;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public BaseResponse<PromotionDTO> update(@PathVariable("id") Integer id, @RequestBody  @Valid PromotionRequest request){

        return BaseResponse.<PromotionDTO>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .data(promotionMapper.from(promotionService.update(id, request)))
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){

        promotionService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public BaseResponseList<List<PromotionDTO>> findAll(@RequestParam(value = "page", defaultValue = "${spring.pagination.default.page}") Integer page, @RequestParam(value = "size", defaultValue = "${spring.pagination.default.size}") Integer size){

        BaseDataList<PromotionEntity> list = promotionService.findAll(page, size);
        return BaseResponseList.<List<PromotionDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .pagination(list.getPagination())
                .data(promotionMapper.from(list.getData()))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public BaseResponse<PromotionDTO> findById(@PathVariable("id") Integer id){

        return BaseResponse.<PromotionDTO>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .data(promotionMapper.from(promotionService.findById(id)))
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BaseResponse<PromotionDTO> create(@RequestBody @Valid PromotionRequest request){

        return BaseResponse.<PromotionDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("success")
                .data(promotionMapper.from(promotionService.create(promotionMapper.from(promotionMapper.from(request)))))
                .build();
    }
}
