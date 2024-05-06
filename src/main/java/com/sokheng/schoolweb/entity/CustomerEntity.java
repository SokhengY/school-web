package com.sokheng.schoolweb.entity;

import com.sokheng.schoolweb.utils.common_enum.OccupationEnum;
import com.sokheng.schoolweb.utils.common_enum.GenderEnum;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private Timestamp dob;

    @Enumerated(EnumType.STRING)
    private OccupationEnum occupation;

    private String phoneNumber;

    private String email;

    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "registration",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<CourseEntity> courseEntities;
}
