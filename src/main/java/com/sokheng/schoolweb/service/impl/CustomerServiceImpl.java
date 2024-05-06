package com.sokheng.schoolweb.service.impl;

import com.sokheng.schoolweb.entity.CustomerEntity;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.repository.CustomerRepository;
import com.sokheng.schoolweb.service.interfaces.CustomerService;
import com.sokheng.schoolweb.utils.BaseDataList;
import com.sokheng.schoolweb.utils.BasePagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public BaseDataList<CustomerEntity> findAll(Integer page, Integer size) {

        Page<CustomerEntity> entities = customerRepository.findAll(PageRequest.of(page-1, size));
        return BaseDataList.<CustomerEntity>builder()
                .pagination(new BasePagination(page, size, entities.getTotalElements(), entities.getTotalPages()))
                .data(entities.getContent())
                .build();
    }

    @Override
    public void existsById(Integer id) {

        boolean exists = customerRepository.existsById(id);
        if (!exists) throw new NotFoundException("customer not exists");
    }

    @Override
    public CustomerEntity findById(Integer id) {

        this.existsById(id);
        return customerRepository.findById(id).orElse(new CustomerEntity());
    }
}
