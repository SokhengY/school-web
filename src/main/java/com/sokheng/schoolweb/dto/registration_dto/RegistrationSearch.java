package com.sokheng.schoolweb.dto.registration_dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationSearch {

    private Integer page;

    private  Integer size;

    private Integer customerId;

    private String timestamp;
}
