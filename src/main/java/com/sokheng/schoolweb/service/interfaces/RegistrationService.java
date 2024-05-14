package com.sokheng.schoolweb.service.interfaces;

import com.sokheng.schoolweb.dto.registration_dto.RegistrationDTO;
import com.sokheng.schoolweb.dto.registration_dto.RegistrationSearch;
import com.sokheng.schoolweb.entity.RegistrationEntity;
import com.sokheng.schoolweb.utils.BaseDataList;

import java.util.List;

public interface RegistrationService {

    RegistrationEntity findById(Integer id);

    BaseDataList<RegistrationEntity> findAll(RegistrationSearch search);

    List<RegistrationEntity> register(RegistrationDTO dto);
}
