package com.sokheng.schoolweb.dto.promotion_dto;

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

    @NotNull(message = "startDate is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
    private String startDate;

    @NotNull(message = "endDate is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
    private String endDate;

}
