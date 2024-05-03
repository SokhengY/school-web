package com.sokheng.schoolweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course_payment")
public class CoursePaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentEntity paymentEntity;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntity;
}
