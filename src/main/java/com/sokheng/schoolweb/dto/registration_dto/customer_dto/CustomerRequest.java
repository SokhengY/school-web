package com.sokheng.schoolweb.dto.registration_dto.customer_dto;

import com.sokheng.schoolweb.utils.common_enum.GenderEnum;
import com.sokheng.schoolweb.utils.common_enum.OccupationEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Gender id required")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @NotNull(message = "fromTime is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "dob format should be yyyy-MM-dd")
    private String dob;

    @NotNull(message = "occupation is required")
    @Enumerated(EnumType.STRING)
    private OccupationEnum occupation;

    @NotBlank(message = "Invalid phone number")
    @NotNull(message = "Phone number is required")
    private String phoneNumber;


    @NotBlank(message = "Invalid email")
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String address;
}
