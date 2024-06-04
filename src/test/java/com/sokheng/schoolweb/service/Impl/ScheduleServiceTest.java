package com.sokheng.schoolweb.service.Impl;

import com.sokheng.schoolweb.dto.course_dto.schedule_dto.ScheduleRequest;
import com.sokheng.schoolweb.entity.ScheduleEntity;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.repository.ScheduleRepository;
import com.sokheng.schoolweb.service.impl.ScheduleServiceImpl;
import com.sokheng.schoolweb.utils.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleServiceImpl scheduleServiceImpl;

    @BeforeEach
    public void init(){

        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setFromTime(Timestamp.valueOf("2024-06-01 10:00:00"));
        schedule.setToTime(Timestamp.valueOf("2024-06-01 12:00:00"));
        schedule.setDeleted(false);
        scheduleRepository.save(schedule);
    }

    @Test
    public void testUpdate(){

        //given
        Integer id = 1;
        ScheduleRequest request = new ScheduleRequest();
        request.setFromTime("2024-06-01 10:00:00");
        request.setToTime("2024-06-01 12:00:00");

        ScheduleEntity entity = new ScheduleEntity();
        entity.setId(id);
        entity.setFromTime(Timestamp.valueOf("2024-06-01 10:00:00"));
        entity.setToTime(Timestamp.valueOf("2024-06-01 12:00:00"));
        //when
        when(scheduleRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(true);
        when(scheduleRepository.findById(id)).thenReturn(Optional.of(entity));
        when(scheduleRepository.save(any(ScheduleEntity.class))).thenReturn(entity);
        ScheduleEntity updatedEntity = scheduleServiceImpl.update(id, request);
        //then
        assertNotNull(updatedEntity);
        assertEquals(Timestamp.valueOf("2024-06-01 10:00:00"), updatedEntity.getFromTime());
        assertEquals(Timestamp.valueOf("2024-06-01 12:00:00"), updatedEntity.getToTime());
        verify(scheduleRepository, times(1)).existsByIdAndIsDeletedFalse(id);
        verify(scheduleRepository, times(1)).findById(id);
        verify(scheduleRepository, times(2)).save(any(ScheduleEntity.class));
    }

    @Test
    public void testFindById(){

        //given
        Integer id = 1;
        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setId(1);
        schedule.setDeleted(false);
        //when
        when(scheduleRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(true);
        when(scheduleRepository.findById(id)).thenReturn(Optional.of(schedule));
        ScheduleEntity entity = scheduleServiceImpl.findById(id);
        //then
        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.isDeleted()).isFalse();
        verify(scheduleRepository, times(1)).existsByIdAndIsDeletedFalse(id);
        verify(scheduleRepository, times(1)).findById(id);
    }

    @Test
    public void testDelete(){

        //given
        Integer id = 1;
        //when
        when(scheduleRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(true);
        scheduleServiceImpl.delete(id);
        //then
        verify(scheduleRepository, times(1)).existsByIdAndIsDeletedFalse(id);
        verify(scheduleRepository, times(1)).updateStatusById(id);
    }

    @Test
    public void testExistsByIdAndStatusError(){

        //given
        Integer id = 1;
        //when
        when(scheduleRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(false);
        assertThrows(NotFoundException.class, ()->scheduleServiceImpl.existsByIdAndStatus(id), "schedule not exists");
        //then
        verify(scheduleRepository, times(1)).existsByIdAndIsDeletedFalse(id);
    }

    @Test
    public void testExistsByIdAndStatusSuccess(){

        //given
        Integer id = 1;
        //when
        when(scheduleRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(true);
        scheduleServiceImpl.existsByIdAndStatus(id);
        //then
        verify(scheduleRepository, times(1)).existsByIdAndIsDeletedFalse(id);
    }
}
