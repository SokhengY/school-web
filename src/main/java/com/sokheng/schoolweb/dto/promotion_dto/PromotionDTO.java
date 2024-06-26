package com.sokheng.schoolweb.dto.promotion_dto;

import com.sokheng.schoolweb.utils.common_enum.CurrencyEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDTO {

    private Integer id;

    private String requirement;

    private Double amount;

    private CurrencyEnum currency;

    private Timestamp startDate;

    private Timestamp endDate;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private boolean isDeleted;

}
