package com.sokheng.schoolweb.dto.course_dto.schedule_dto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {

    private Integer id;

    private Timestamp fromTime;

    private Timestamp toTime;

    private boolean isDeleted;
}
