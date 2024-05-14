package com.sokheng.schoolweb.entity;

import com.sokheng.schoolweb.utils.common_enum.CurrencyEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_detail")
public class PaymentDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double totalAmount;

    private Double amountPaid;

    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentEntity paymentEntity;

}
