package com.sokheng.schoolweb.controller;

import com.sokheng.schoolweb.dto.course_dto.schedule_dto.ScheduleDTO;
import com.sokheng.schoolweb.dto.course_dto.schedule_dto.ScheduleRequest;
import com.sokheng.schoolweb.mapper.ScheduleMapper;
import com.sokheng.schoolweb.service.interfaces.ScheduleService;
import com.sokheng.schoolweb.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1.0.0/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public BaseResponse<ScheduleDTO> update(@PathVariable("id") Integer id, @RequestBody ScheduleRequest request){

        return BaseResponse.<ScheduleDTO>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .data(scheduleMapper.from(scheduleService.update(id, request)))
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){

        scheduleService.delete(id);
    }
}
