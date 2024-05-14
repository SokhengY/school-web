package com.sokheng.schoolweb.dto.course_dto.schedule_dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {

    @NotNull(message = "fromTime is required")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12]\\d|3[01])\\s([01]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9])$", message = "fromTime format should be yyyy-MM-dd HH:mm:ss")
    private String fromTime;

    @NotNull(message = "toTime is required")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12]\\d|3[01])\\s([01]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9])$", message = "toTime format should be yyyy-MM-dd HH:mm:ss")
    private String toTime;

}
