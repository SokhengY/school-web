package com.sokheng.schoolweb.dto.course_dto;

import com.sokheng.schoolweb.dto.course_dto.price_dto.PriceRequest;
import com.sokheng.schoolweb.dto.course_dto.schedule_dto.ScheduleRequest;
import com.sokheng.schoolweb.utils.common_enum.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {

    private String name;

    @NotNull(message = "detail must not be null")
    @Size(max = 255, message = "detail must not exceed 255 characters")
    private String detail;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @NotNull(message = "required category id")
    private Integer categoryId;

    @Valid
    private List<PriceRequest> price;

    @Valid
    private List<ScheduleRequest> schedule;

}
