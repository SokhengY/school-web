package com.sokheng.schoolweb.mapper;

import com.sokheng.schoolweb.dto.course_dto.schedule_dto.ScheduleDTO;
import com.sokheng.schoolweb.entity.ScheduleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    List<ScheduleDTO> toDto(List<ScheduleEntity> list);

    List<ScheduleEntity> from(List<ScheduleDTO> dtos);

    ScheduleEntity from(ScheduleDTO dto);

    ScheduleDTO from(ScheduleEntity entity);
}
