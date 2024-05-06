package com.sokheng.schoolweb.service.interfaces;

import com.sokheng.schoolweb.dto.registration_dto.RegistrationDTO;
import com.sokheng.schoolweb.entity.RegistrationEntity;

import java.util.List;

public interface RegistrationService {

    List<RegistrationEntity> register(RegistrationDTO dto);
}
