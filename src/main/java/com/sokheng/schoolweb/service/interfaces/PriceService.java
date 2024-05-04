package com.sokheng.schoolweb.service.interfaces;

import com.sokheng.schoolweb.dto.course_dto.price_dto.PriceRequest;
import com.sokheng.schoolweb.entity.PriceEntity;

public interface PriceService {

    PriceEntity updatePrice(Integer id, PriceRequest request);

    PriceEntity findById(Integer id);

    void existsById(Integer id);
}
