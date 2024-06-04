package com.sokheng.schoolweb.service.Impl;

import com.sokheng.schoolweb.dto.category_dto.CategoryDTO;
import com.sokheng.schoolweb.dto.course_dto.schedule_dto.ScheduleRequest;
import com.sokheng.schoolweb.dto.promotion_dto.PromotionDTO;
import com.sokheng.schoolweb.dto.promotion_dto.PromotionRequest;
import com.sokheng.schoolweb.entity.CategoryEntity;
import com.sokheng.schoolweb.entity.PromotionEntity;
import com.sokheng.schoolweb.entity.ScheduleEntity;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.mapper.PromotionMapper;
import com.sokheng.schoolweb.repository.CourseRepository;
import com.sokheng.schoolweb.repository.PromotionRepository;
import com.sokheng.schoolweb.service.impl.PromotionServiceImpl;
import com.sokheng.schoolweb.utils.BaseDataList;
import com.sokheng.schoolweb.utils.DateTimeUtil;
import com.sokheng.schoolweb.utils.common_enum.CurrencyEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PromotionServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private PromotionRepository promotionRepository;
    @InjectMocks
    private PromotionServiceImpl promotionServiceImpl;

    @Test
    public void testDeleteWithoutCourses() {

        //given
        Integer id = 1;
        List<Integer> courseIds = Collections.emptyList();
        //when
        when(promotionRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(true);
        doNothing().when(promotionRepository).updateStatusById(id);
        when(courseRepository.findAllByPromotionId(id)).thenReturn(courseIds);
        promotionServiceImpl.delete(id);
        //then
        verify(promotionRepository).updateStatusById(id);
        verify(courseRepository).findAllByPromotionId(id);
        verify(courseRepository, never()).deletePromotionId(anyInt());
    }

//    @Test
//    public void testDeleteWithCourse(){
//
//        //given
//        Integer id = 1;
//        List<Integer> courseIds = Arrays.asList(10, 20, 30);
//        //when
//        doNothing().when(promotionServiceImpl).existsById(id);
//        when(courseRepository.findAllByPromotionId(id)).thenReturn(courseIds);
//        doNothing().when(promotionRepository).updateStatusById(id);
//        doNothing().when(courseRepository).deletePromotionId(anyInt());
//        promotionServiceImpl.delete(id);
//        //then
//        verify(promotionServiceImpl).existsById(id);
//        verify(promotionRepository).updateStatusById(id);
//        verify(courseRepository).findAllByPromotionId(id);
//        for (Integer courseId : courseIds) {
//            verify(courseRepository).deletePromotionId(courseId);
//        }
//    }

    @Test
    public void testUpdate(){

        // given
        Integer id = 1;
        PromotionEntity promotionEntity = new PromotionEntity();
        promotionEntity.setId(id);
        promotionEntity.setRequirement("Old Requirement");
        promotionEntity.setAmount(100.0);
        promotionEntity.setCurrency(CurrencyEnum.USD);

        PromotionRequest request = new PromotionRequest();
        request.setRequirement("New Requirement");
        request.setAmount(200.0);
        request.setCurrency(CurrencyEnum.USD);
        request.setStartDate("2024-01-01 12:00:00");
        request.setEndDate("2024-12-31 12:00:00");
        //when
        when(promotionRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(true);
        when(promotionRepository.findById(id)).thenReturn(Optional.of(promotionEntity));
        when(promotionRepository.save(any(PromotionEntity.class))).thenReturn(promotionEntity);
        PromotionEntity result = promotionServiceImpl.update(id, request);
        //then
        assertNotNull(result);
        assertEquals("New Requirement", result.getRequirement());
        assertEquals(200.0, result.getAmount());
        verify(promotionRepository, times(1)).existsByIdAndIsDeletedFalse(id);
        verify(promotionRepository, times(1)).findById(id);
        verify(promotionRepository, times(1)).save(promotionEntity);
    }

    @Test
    public void testFindAll(){

        // given
        Integer page = 1;
        Integer size = 10;

        PromotionEntity promotion1 = new PromotionEntity();
        promotion1.setId(1);
        promotion1.setRequirement("testing1");

        PromotionEntity promotion2 = new PromotionEntity();
        promotion2.setId(2);
        promotion2.setRequirement("testing2");

        List<PromotionEntity> promotions = new ArrayList<>();
        promotions.add(promotion1);
        promotions.add(promotion2);

        Page<PromotionEntity> promotionPage = new PageImpl<>(promotions, PageRequest.of(0, size), promotions.size());

        //when
        when(promotionRepository.findAll(any(Pageable.class))).thenReturn(promotionPage);
        BaseDataList<PromotionEntity> result = promotionServiceImpl.findAll(page, size);

        // then
        assertNotNull(result);
        assertEquals(2, result.getData().size());
        assertEquals(page, result.getPagination().getPage());
        assertEquals(size, result.getPagination().getSize());
        assertEquals(promotions.size(), result.getPagination().getTotalItems());
        assertEquals(1, result.getPagination().getTotalPages());

        verify(promotionRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void testFindById(){

        //given
        Integer id = 1;
        PromotionEntity promotion = new PromotionEntity();
        promotion.setId(id);
        promotion.setRequirement("testing");
        promotion.setDeleted(false);
        //when
        when(promotionRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(true);
        when(promotionRepository.findById(id)).thenReturn(Optional.of(promotion));
        PromotionEntity result = promotionServiceImpl.findById(id);
        //then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("testing", result.getRequirement());
        verify(promotionRepository, times(1)).existsByIdAndIsDeletedFalse(id);
        verify(promotionRepository, times(1)).findById(id);
    }

    @Test
    public void testExistsByIdError(){

        //given
        Integer id = 1;
        //when
        when(promotionRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(false);
        assertThrows(NotFoundException.class, () -> promotionServiceImpl.existsById(id), "promotion not exists");
        //then
        verify(promotionRepository, times(1)).existsByIdAndIsDeletedFalse(id);
    }

    @Test
    public void testCreate(){

        //given
        PromotionEntity entity = new PromotionEntity();
        entity.setRequirement("testing");
        entity.setDeleted(false);
        //when
        when(promotionRepository.save(any(PromotionEntity.class))).thenReturn(entity);
        PromotionEntity result = promotionServiceImpl.create(entity);
        //then
        assertNotNull(result);
        assertThat(entity.getRequirement()).isEqualTo("testing");
        verify(promotionRepository, times(1)).save(entity);
    }
}
