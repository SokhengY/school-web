package com.sokheng.schoolweb.dto.payment_dto;

import com.sokheng.schoolweb.utils.common_enum.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailDTO {

    private Integer id;

    private Double totalAmount;

    private Double amountPaid;

    private CurrencyEnum currency;

    private Timestamp createdAt;
}
