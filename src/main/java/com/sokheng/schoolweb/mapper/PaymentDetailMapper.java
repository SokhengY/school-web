package com.sokheng.schoolweb.mapper;

import com.sokheng.schoolweb.dto.payment_dto.PaymentDTO;
import com.sokheng.schoolweb.dto.payment_dto.PaymentDetailDTO;
import com.sokheng.schoolweb.entity.PaymentDetailEntity;
import com.sokheng.schoolweb.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentDetailMapper {

    PaymentDetailDTO from(PaymentDetailEntity entity);
}
