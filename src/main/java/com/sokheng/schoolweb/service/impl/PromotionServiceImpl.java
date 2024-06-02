package com.sokheng.schoolweb.service.impl;

import com.sokheng.schoolweb.dto.promotion_dto.PromotionRequest;
import com.sokheng.schoolweb.entity.PromotionEntity;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.mapper.PromotionMapper;
import com.sokheng.schoolweb.repository.CourseRepository;
import com.sokheng.schoolweb.repository.PromotionRepository;
import com.sokheng.schoolweb.service.interfaces.PromotionService;
import com.sokheng.schoolweb.utils.BaseDataList;
import com.sokheng.schoolweb.utils.BasePagination;
import com.sokheng.schoolweb.utils.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final CourseRepository courseRepository;
    private final PromotionMapper promotionMapper;

    @Override
    public PromotionEntity update(Integer id, PromotionRequest request) {

        PromotionEntity entity = this.findById(id);
        entity.setRequirement(request.getRequirement());
        entity.setAmount(request.getAmount());
        entity.setCurrency(request.getCurrency());
        entity.setStartDate(DateTimeUtil.stringToTimestamp(request.getStartDate()));
        entity.setEndDate(DateTimeUtil.stringToTimestamp(request.getEndDate()));
        entity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return promotionRepository.save(entity);
    }

    @Transactional
    @Override
    public void delete(Integer id) {

        this.existsById(id);
        promotionRepository.updateStatusById(id);
        //clear promotion id from course
        List<Integer> courseIds = courseRepository.findAllByPromotionId(id);
        if (courseIds.size() > 0){
            for (Integer courseId : courseIds){
                courseRepository.deletePromotionId(courseId);
            }
        }
    }

    @Override
    public BaseDataList<PromotionEntity> findAll(Integer page, Integer size) {

        if (page <= 0) page = 1;
        if (size <= 0) size = 10;
        Page<PromotionEntity> entityPage = promotionRepository.findAll(PageRequest.of(page-1, size));
        return BaseDataList.<PromotionEntity>builder()
                .pagination(new BasePagination(page, size, entityPage.getTotalElements(), entityPage.getTotalPages()))
                .data(entityPage.getContent())
                .build();
    }

    @Override
    public PromotionEntity findById(Integer id) {

        this.existsById(id);
        return promotionRepository.findById(id).orElse(new PromotionEntity());
    }

    @Override
    public void existsById(Integer id) {

        boolean exists = promotionRepository.existsByIdAndIsDeletedFalse(id);
        if (!exists) throw new NotFoundException("promotion not exists");
    }

    @Override
    public PromotionEntity create(PromotionEntity entity) {

        return promotionRepository.save(entity);
    }
}
