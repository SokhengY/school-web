package com.sokheng.schoolweb.service.impl;

import com.sokheng.schoolweb.dto.course_dto.schedule_dto.ScheduleRequest;
import com.sokheng.schoolweb.entity.ScheduleEntity;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.repository.ScheduleRepository;
import com.sokheng.schoolweb.service.interfaces.ScheduleService;
import com.sokheng.schoolweb.utils.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleEntity update(Integer id, ScheduleRequest request) {

        ScheduleEntity entity = this.findById(id);
        entity.setFromTime(DateTimeUtil.stringToTimestamp(request.getFromTime()));
        entity.setToTime(DateTimeUtil.stringToTimestamp(request.getToTime()));
        return scheduleRepository.save(entity);
    }

    @Override
    public ScheduleEntity findById(Integer id) {

        this.existsByIdAndStatus(id);
        return scheduleRepository.findById(id).orElse(new ScheduleEntity());
    }

    @Override
    public void delete(Integer id) {

        this.existsByIdAndStatus(id);
        scheduleRepository.updateStatusById(id);
    }

    @Override
    public void existsByIdAndStatus(Integer id) {

        boolean exists = scheduleRepository.existsByIdAndIsDeletedFalse(id);
        if (!exists) throw new NotFoundException("schedule not exists");
    }
}
