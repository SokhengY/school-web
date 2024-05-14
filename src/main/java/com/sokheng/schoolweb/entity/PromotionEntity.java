package com.sokheng.schoolweb.entity;

import com.sokheng.schoolweb.utils.common_enum.CurrencyEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promotion")
public class PromotionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String requirement;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    private Timestamp startDate;

    private Timestamp endDate;

    @CreationTimestamp
    private Timestamp createdAt;

    private Timestamp updatedAt;

    private boolean isDeleted = false;

    @OneToMany(mappedBy = "promotionEntity")
    private List<CourseEntity> courseEntities;

}
