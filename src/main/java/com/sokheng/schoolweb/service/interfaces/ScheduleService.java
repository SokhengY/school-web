package com.sokheng.schoolweb.service.interfaces;

import com.sokheng.schoolweb.dto.course_dto.schedule_dto.ScheduleRequest;
import com.sokheng.schoolweb.entity.ScheduleEntity;

public interface ScheduleService {

    ScheduleEntity update(Integer id, ScheduleRequest request);

    ScheduleEntity findById(Integer id);

    void delete(Integer id);

    void existsByIdAndStatus(Integer id);
}
