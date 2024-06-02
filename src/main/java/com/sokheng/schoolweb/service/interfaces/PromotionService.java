package com.sokheng.schoolweb.service.interfaces;

import com.sokheng.schoolweb.dto.promotion_dto.PromotionRequest;
import com.sokheng.schoolweb.entity.PromotionEntity;
import com.sokheng.schoolweb.utils.BaseDataList;

public interface PromotionService {

    PromotionEntity update(Integer id, PromotionRequest request);

    void delete(Integer id);

    BaseDataList<PromotionEntity> findAll(Integer page, Integer size);

    PromotionEntity findById(Integer id);

    void existsById(Integer id);

    PromotionEntity create(PromotionEntity entity);
}
