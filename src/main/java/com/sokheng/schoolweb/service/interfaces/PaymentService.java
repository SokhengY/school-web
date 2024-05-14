package com.sokheng.schoolweb.service.interfaces;

import com.sokheng.schoolweb.dto.payment_dto.PaymentDTO;
import com.sokheng.schoolweb.dto.payment_dto.PaymentDetailRequest;
import com.sokheng.schoolweb.dto.payment_dto.PaymentRequest;
import com.sokheng.schoolweb.entity.PaymentDetailEntity;
import com.sokheng.schoolweb.entity.PaymentEntity;
import com.sokheng.schoolweb.utils.BaseDataList;

import java.util.List;

public interface PaymentService {

    PaymentDetailEntity createPaymentDetail(PaymentDetailRequest request);

    BaseDataList<PaymentEntity> findAll(Integer page, Integer size);

    /**
     *user can pay multiple course include promotion course and normal course
     */
    PaymentEntity create(PaymentRequest request);
}
