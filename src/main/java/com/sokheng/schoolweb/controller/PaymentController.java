package com.sokheng.schoolweb.controller;

import com.sokheng.schoolweb.dto.payment_dto.PaymentDTO;
import com.sokheng.schoolweb.dto.payment_dto.PaymentDetailDTO;
import com.sokheng.schoolweb.dto.payment_dto.PaymentDetailRequest;
import com.sokheng.schoolweb.dto.payment_dto.PaymentRequest;
import com.sokheng.schoolweb.entity.PaymentDetailEntity;
import com.sokheng.schoolweb.entity.PaymentEntity;
import com.sokheng.schoolweb.exception.BadRequestException;
import com.sokheng.schoolweb.mapper.PaymentDetailMapper;
import com.sokheng.schoolweb.mapper.PaymentMapper;
import com.sokheng.schoolweb.service.interfaces.PaymentService;
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
@RequestMapping("api/v1.0.0/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;
    private final PaymentDetailMapper paymentDetailMapper;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("payment-detail")
    public BaseResponse<PaymentDetailDTO> createPaymentDetail(@RequestBody PaymentDetailRequest request){

        PaymentDetailEntity paymentDetail = paymentService.createPaymentDetail(request);
        PaymentDetailDTO from = paymentDetailMapper.from(paymentDetail);
        return BaseResponse.<PaymentDetailDTO>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .data(from)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public BaseResponseList<List<PaymentDTO>> findAll(@RequestParam(value = "page", defaultValue = "${spring.pagination.default.page}") Integer page, @RequestParam(value = "size", defaultValue = "${spring.pagination.default.size}") Integer size){

        BaseDataList<PaymentEntity> list = paymentService.findAll(page, size);
        return BaseResponseList.<List<PaymentDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .pagination(list.getPagination())
                .data(paymentMapper.from(list.getData()))
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BaseResponse<PaymentDTO> create(@RequestBody @Valid PaymentRequest request){

        if (request.getCourseIds().size() == 0){
            throw new BadRequestException("course id is required");
        }
        return BaseResponse.<PaymentDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("success")
                .data(paymentMapper.from(paymentService.create(request)))
                .build();
    }
}
