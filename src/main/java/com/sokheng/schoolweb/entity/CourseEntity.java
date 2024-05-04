package com.sokheng.schoolweb.entity;

import com.sokheng.schoolweb.utils.common_enum.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String detail;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @CreationTimestamp
    private Timestamp createdAt;

//    @UpdateTimestamp
//    @Temporal(TemporalType.TIMESTAMP)// Ensure that the property is not set when creating the entity
    private Timestamp updatedAt;

    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private PromotionEntity promotionEntity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "courseEntity")
    private List<PriceEntity> priceEntities;

    @OneToMany(mappedBy = "courseEntity")
    private List<ScheduleEntity> scheduleEntities;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "registration", joinColumns = {@JoinColumn(name = "course_id")}, inverseJoinColumns = {@JoinColumn(name = "customer_id")})
//    private Set<RegistrationEntity> registrationEntities = new HashSet<>();

}
