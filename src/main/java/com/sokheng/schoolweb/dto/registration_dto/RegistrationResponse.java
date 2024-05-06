package com.sokheng.schoolweb.dto.registration_dto;

import com.sokheng.schoolweb.dto.course_dto.CourseDTO;
import com.sokheng.schoolweb.dto.promotion_dto.PromotionDTO;
import com.sokheng.schoolweb.dto.registration_dto.customer_dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {

    private Integer id;

    private Timestamp createdAt;

    private CourseDTO course;

    private CustomerDTO customer;

    private PromotionDTO promotion;

}
