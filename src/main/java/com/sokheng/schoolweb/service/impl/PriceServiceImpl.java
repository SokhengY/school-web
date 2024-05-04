package com.sokheng.schoolweb.service.impl;

import com.sokheng.schoolweb.dto.course_dto.price_dto.PriceRequest;
import com.sokheng.schoolweb.entity.PriceEntity;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.repository.PriceRepository;
import com.sokheng.schoolweb.service.interfaces.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public PriceEntity updatePrice(Integer id, PriceRequest request) {

        PriceEntity price = this.findById(id);
        price.setAmount(request.getAmount());
        price.setCurrency(request.getCurrency());
        price.setType(request.getType());
        return priceRepository.save(price);
    }

    @Override
    public PriceEntity findById(Integer id) {

        this.existsById(id);
        return priceRepository.findById(id).orElse(new PriceEntity());
    }

    @Override
    public void existsById(Integer id) {

        boolean exists = priceRepository.existsById(id);
        if (!exists) throw new NotFoundException("price not exists");
    }
}
