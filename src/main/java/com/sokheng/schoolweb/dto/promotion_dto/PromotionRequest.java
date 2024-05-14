package com.sokheng.schoolweb.dto.promotion_dto;

import com.sokheng.schoolweb.utils.common_enum.CurrencyEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionRequest {

    @NotNull(message = "requirement must not be null")
    @Size(max = 255, message = "requirement must not exceed 255 characters")
    private String requirement;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    @NotNull(message = "startDate is required")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12]\\d|3[01])\\s([01]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9])$", message = "startDate format should be yyyy-MM-dd HH:mm:ss")
    private String startDate;

    @NotNull(message = "endDate is required")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12]\\d|3[01])\\s([01]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9])$", message = "endDate format should be yyyy-MM-dd HH:mm:ss")
    private String endDate;

}
