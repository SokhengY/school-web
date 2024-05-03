package com.sokheng.schoolweb.entity;

import com.sokheng.schoolweb.utils.common_enum.OccupationEnum;
import com.sokheng.schoolweb.utils.common_enum.GenderEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
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

    private Integer age;

    @Enumerated(EnumType.STRING)
    private OccupationEnum occupation;

    private String phoneNumber;

    private String email;

    private String address;

    @ManyToMany
    @JoinTable(
            name = "registration",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<CourseEntity> courseEntities = new HashSet<>();
}
