package com.sokheng.schoolweb.dto.CourseDTO;

import com.sokheng.schoolweb.dto.CategoryDTO.CategoryDTO;
import com.sokheng.schoolweb.utils.common_enum.StatusEnum;
import lombok.*;

import java.sql.Timestamp;

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

}
