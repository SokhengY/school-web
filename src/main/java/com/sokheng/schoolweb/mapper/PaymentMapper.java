package com.sokheng.schoolweb.mapper;

import com.sokheng.schoolweb.dto.payment_dto.PaymentDTO;
import com.sokheng.schoolweb.dto.payment_dto.PaymentDetailDTO;
import com.sokheng.schoolweb.entity.PaymentDetailEntity;
import com.sokheng.schoolweb.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    List<PaymentDTO> from(List<PaymentEntity> list);

    @Mapping(source = "customerEntity", target = "customer")
    @Mapping(source = "paymentDetailEntities", target = "paymentDetail")
    @Mapping(source = "courseEntities", target = "course")
    PaymentDTO from(PaymentEntity entity);
}
