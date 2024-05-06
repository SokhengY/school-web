package com.sokheng.schoolweb.service.impl;

import com.sokheng.schoolweb.dto.registration_dto.RegistrationDTO;
import com.sokheng.schoolweb.entity.CourseEntity;
import com.sokheng.schoolweb.entity.CustomerEntity;
import com.sokheng.schoolweb.entity.PromotionEntity;
import com.sokheng.schoolweb.entity.RegistrationEntity;
import com.sokheng.schoolweb.mapper.CustomerMapper;
import com.sokheng.schoolweb.repository.CustomerRepository;
import com.sokheng.schoolweb.repository.RegistrationRepository;
import com.sokheng.schoolweb.service.interfaces.CourseService;
import com.sokheng.schoolweb.service.interfaces.PromotionService;
import com.sokheng.schoolweb.service.interfaces.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final CustomerRepository customerRepository;
    private final CourseService courseService;
    private final PromotionService promotionService;
    private final CustomerMapper customerMapper;

    @Transactional
    @Override
    public List<RegistrationEntity> register(RegistrationDTO dto) {

        List<RegistrationEntity> entities = new ArrayList<>();
        //insert customer
        CustomerEntity customer = customerRepository.save(customerMapper.from(dto.getCustomer()));
        //check course, promotion and add to registration list
        for (Integer courseId : dto.getCourseId()){
            //check course
            CourseEntity course = courseService.findById(courseId);
            //check promotion
            if (Objects.nonNull(dto.getPromotion().getId())){
                PromotionEntity promotion = promotionService.findById(dto.getPromotion().getId());
                //add registration to list with promotion
                RegistrationEntity registration = new RegistrationEntity();
                registration.setCustomerEntity(customer);
                registration.setCourseEntity(course);
                registration.setPromotionEntity(promotion);
                entities.add(registration);
            }else {
                //add registration to list without promotion
                RegistrationEntity registration = new RegistrationEntity();
                registration.setCustomerEntity(customer);
                registration.setCourseEntity(course);
                entities.add(registration);
            }
        }
        return registrationRepository.saveAll(entities);
    }


}
