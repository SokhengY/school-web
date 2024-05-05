package com.sokheng.schoolweb.dto.promotion_dto;

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

    private Timestamp startDate;

    private Timestamp endDate;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private boolean isDeleted;

}
