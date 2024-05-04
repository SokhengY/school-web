package com.sokheng.schoolweb.dto.course_dto.price_dto;

import com.sokheng.schoolweb.utils.common_enum.CurrencyEnum;
import com.sokheng.schoolweb.utils.common_enum.OccupationEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequest {

    private Double amount;

    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    @Enumerated(EnumType.STRING)
    private OccupationEnum type;
}
