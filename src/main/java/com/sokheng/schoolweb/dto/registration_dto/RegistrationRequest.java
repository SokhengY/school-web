package com.sokheng.schoolweb.dto.registration_dto;

import com.sokheng.schoolweb.dto.registration_dto.customer_dto.CustomerRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    private List<Integer> courseId;

    private Integer promotionId;

    @Valid
    private CustomerRequest customer;

}
