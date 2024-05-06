package com.sokheng.schoolweb.dto.registration_dto.customer_dto;

import com.sokheng.schoolweb.dto.course_dto.CourseDTO;
import com.sokheng.schoolweb.utils.common_enum.GenderEnum;
import com.sokheng.schoolweb.utils.common_enum.OccupationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Integer id;

    private String name;

    private GenderEnum gender;

    private Timestamp dob;

    private OccupationEnum occupation;

    private String phoneNumber;

    private String email;

    private String address;

    private List<CourseDTO> course;
}
