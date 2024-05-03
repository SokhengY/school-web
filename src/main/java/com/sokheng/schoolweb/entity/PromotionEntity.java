package com.sokheng.schoolweb.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private Timestamp startDate;

    private Timestamp endDate;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private boolean isDeleted = false;

    @OneToMany(mappedBy = "promotionEntity")
    private List<CourseEntity> courseEntities;

}
