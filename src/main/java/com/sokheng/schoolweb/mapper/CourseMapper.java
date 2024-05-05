package com.sokheng.schoolweb.mapper;

import com.sokheng.schoolweb.dto.course_dto.CourseDTO;
import com.sokheng.schoolweb.dto.course_dto.CourseRequest;
import com.sokheng.schoolweb.dto.course_dto.schedule_dto.ScheduleDTO;
import com.sokheng.schoolweb.dto.course_dto.schedule_dto.ScheduleRequest;
import com.sokheng.schoolweb.entity.CourseEntity;
import com.sokheng.schoolweb.utils.DateTimeUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    List<CourseDTO> from(List<CourseEntity> list);

    @Mapping(source = "price", target = "priceEntities")
    CourseEntity from(CourseDTO dto);

    @Mapping(source = "promotionEntity", target = "promotion")
    @Mapping(source = "scheduleEntities", target = "schedule")
    @Mapping(source = "categoryEntity", target = "category")
    @Mapping(source = "priceEntities", target = "price")
    CourseDTO from(CourseEntity entity);

    @Mapping(target = "schedule", expression = "java(mapSchedule(request))")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "promotionId", target = "promotion.id")
    @Mapping(source = "price", target = "price")
    CourseDTO from(CourseRequest request);

    default List<ScheduleDTO> mapSchedule(CourseRequest courseRequest) {

        List<ScheduleDTO> dtos = new ArrayList<>();
        for (ScheduleRequest request : courseRequest.getSchedule()){
            ScheduleDTO dto = new ScheduleDTO();
            dto.setFromTime(DateTimeUtil.stringToTimestamp(request.getFromTime()));
            dto.setToTime(DateTimeUtil.stringToTimestamp(request.getToTime()));
            dtos.add(dto);
        }
        return dtos;
    }

}
