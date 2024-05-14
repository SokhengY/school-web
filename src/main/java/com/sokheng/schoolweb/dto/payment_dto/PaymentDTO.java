package com.sokheng.schoolweb.dto.payment_dto;

import com.sokheng.schoolweb.dto.course_dto.CourseDTO;
import com.sokheng.schoolweb.dto.registration_dto.customer_dto.CustomerDTO;
import com.sokheng.schoolweb.utils.common_enum.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Integer id;

    private StatusEnum status;

    private CustomerDTO customer;

    private Set<PaymentDetailDTO> paymentDetail;

    private Set<CourseDTO> course;
}
