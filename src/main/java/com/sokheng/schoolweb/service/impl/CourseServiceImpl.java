package com.sokheng.schoolweb.service.impl;

import com.sokheng.schoolweb.dto.course_dto.CourseDTO;
import com.sokheng.schoolweb.dto.course_dto.CourseRequest;
import com.sokheng.schoolweb.dto.course_dto.price_dto.PriceRequest;
import com.sokheng.schoolweb.entity.CategoryEntity;
import com.sokheng.schoolweb.entity.CourseEntity;
import com.sokheng.schoolweb.entity.PriceEntity;
import com.sokheng.schoolweb.entity.ScheduleEntity;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.mapper.CourseMapper;
import com.sokheng.schoolweb.mapper.PriceMapper;
import com.sokheng.schoolweb.mapper.PromotionMapper;
import com.sokheng.schoolweb.mapper.ScheduleMapper;
import com.sokheng.schoolweb.repository.CourseRepository;
import com.sokheng.schoolweb.repository.PriceRepository;
import com.sokheng.schoolweb.repository.ScheduleRepository;
import com.sokheng.schoolweb.service.interfaces.CategoryService;
import com.sokheng.schoolweb.service.interfaces.CourseService;
import com.sokheng.schoolweb.service.interfaces.PromotionService;
import com.sokheng.schoolweb.utils.BaseDataList;
import com.sokheng.schoolweb.utils.BasePagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final PriceRepository priceRepository;
    private final ScheduleRepository scheduleRepository;
    private final PromotionService promotionService;
    private final CategoryService categoryService;
    private final CourseMapper courseMapper;
    private final PriceMapper priceMapper;
    private final ScheduleMapper scheduleMapper;
    private final PromotionMapper promotionMapper;

    @Override
    public CourseEntity update(Integer id, CourseDTO dto) {

        CourseEntity course = this.findById(id);
        course.setName(dto.getName());
        course.setDetail(dto.getDetail());
        course.setStatus(dto.getStatus());
        course.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        if (Objects.nonNull(dto.getCategory().getId())){
            course.setCategoryEntity(categoryService.findById(dto.getCategory().getId()));
        }
        return courseRepository.save(course);
    }

    @Override
    public void deleteById(Integer id) {

        this.existsByIdAndStatus(id);
        courseRepository.updateStatusById(id);
    }

    @Override
    public BaseDataList<CourseEntity> findAll(Integer page, Integer size) {

        if (page <= 0) page = 1;
        if (size <= 0) size = 10;
        Page<CourseEntity> entities = courseRepository.findAll(PageRequest.of(page-1, size));
        return BaseDataList.<CourseEntity>builder()
                .pagination(new BasePagination(page, size, entities.getTotalElements(), entities.getTotalPages()))
                .data(entities.getContent())
                .build();
    }

    @Override
    public CourseEntity findById(Integer id) {

        this.existsByIdAndStatus(id);
        return courseRepository.findById(id).orElse(new CourseEntity());
    }

    @Override
    public void existsByIdAndStatus(Integer id) {

        boolean exists = courseRepository.existsByIdAndIsDeletedFalse(id);
        if (!exists) throw new NotFoundException("course not exists");
    }

    @Transactional
    @Override
    public CourseEntity create(CourseDTO dto) {

        CourseEntity course = courseMapper.from(dto);
        course.setCategoryEntity(categoryService.findById(dto.getCategory().getId()));
        if (Objects.nonNull(dto.getPromotion())){
            course.setPromotionEntity(promotionService.findById(dto.getPromotion().getId()));
        }
        //insert course
        CourseEntity courseEntity = courseRepository.save(course);
        //insert price
        List<PriceEntity> priceEntities = priceMapper.fromListDTO(dto.getPrice());
        priceEntities.forEach(price -> price.setCourseEntity(courseEntity));
        List<PriceEntity> priceEntityList = priceRepository.saveAll(priceEntities);
        courseEntity.setPriceEntities(priceEntityList);
        //insert schedule
        List<ScheduleEntity> scheduleEntities = scheduleMapper.from(dto.getSchedule());
        scheduleEntities.forEach(schedule -> schedule.setCourseEntity(courseEntity));
        List<ScheduleEntity> scheduleEntityList = scheduleRepository.saveAll(scheduleEntities);
        courseEntity.setScheduleEntities(scheduleEntityList);
        return courseEntity;
    }
}
