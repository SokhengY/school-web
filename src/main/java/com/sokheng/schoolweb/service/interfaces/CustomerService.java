package com.sokheng.schoolweb.service.interfaces;

import com.sokheng.schoolweb.entity.CustomerEntity;
import com.sokheng.schoolweb.utils.BaseDataList;

public interface CustomerService {

    BaseDataList<CustomerEntity> findAll(Integer page, Integer size);

    void existsById(Integer id);

    CustomerEntity findById(Integer id);
}
