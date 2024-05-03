package com.sokheng.schoolweb.entity;

import com.sokheng.schoolweb.utils.common_enum.CurrencyEnum;
import com.sokheng.schoolweb.utils.common_enum.OccupationEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "price")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    @Enumerated(EnumType.STRING)
    private OccupationEnum type;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntity;
}
