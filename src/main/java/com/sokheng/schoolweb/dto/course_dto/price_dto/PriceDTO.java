package com.sokheng.schoolweb.dto.course_dto.price_dto;

import com.sokheng.schoolweb.utils.common_enum.CurrencyEnum;
import com.sokheng.schoolweb.utils.common_enum.OccupationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO {

    private Integer id;

    private Double amount;

    private CurrencyEnum currency;

    private OccupationEnum type;
}
