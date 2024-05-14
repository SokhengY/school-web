package com.sokheng.schoolweb.dto.payment_dto;

import com.sokheng.schoolweb.utils.common_enum.CurrencyEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailRequest {

    @NotNull(message = "Payment ID is required")
    private Integer paymentId;

    @DecimalMin(value = "0.0", inclusive = false, message = "amountPaid must be greater than 0")
    private double amountPaid;

    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;
}
