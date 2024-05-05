package com.sokheng.schoolweb.dto.course_dto;

import com.sokheng.schoolweb.dto.category_dto.CategoryDTO;
import com.sokheng.schoolweb.dto.course_dto.price_dto.PriceDTO;
import com.sokheng.schoolweb.dto.course_dto.schedule_dto.ScheduleDTO;
import com.sokheng.schoolweb.utils.common_enum.StatusEnum;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Integer id;

    private String name;

    private String detail;

    private StatusEnum status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private boolean isDeleted;

    private CategoryDTO category;

    private List<PriceDTO> price;

    private List<ScheduleDTO> schedule;

}
