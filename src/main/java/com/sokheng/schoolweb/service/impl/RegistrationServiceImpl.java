package com.sokheng.schoolweb.service.impl;

import com.sokheng.schoolweb.dto.registration_dto.RegistrationDTO;
import com.sokheng.schoolweb.dto.registration_dto.RegistrationSearch;
import com.sokheng.schoolweb.entity.CourseEntity;
import com.sokheng.schoolweb.entity.CustomerEntity;
import com.sokheng.schoolweb.entity.PromotionEntity;
import com.sokheng.schoolweb.entity.RegistrationEntity;
import com.sokheng.schoolweb.exception.BadRequestException;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.mapper.CustomerMapper;
import com.sokheng.schoolweb.repository.CustomerRepository;
import com.sokheng.schoolweb.repository.RegistrationRepository;
import com.sokheng.schoolweb.service.interfaces.CourseService;
import com.sokheng.schoolweb.service.interfaces.PromotionService;
import com.sokheng.schoolweb.service.interfaces.RegistrationService;
import com.sokheng.schoolweb.service.specification.RegistrationSpecification;
import com.sokheng.schoolweb.utils.BaseDataList;
import com.sokheng.schoolweb.utils.BasePagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final CustomerRepository customerRepository;
    private final CourseService courseService;
    private final PromotionService promotionService;
    private final CustomerMapper customerMapper;

    void existsById(Integer id){

        boolean exists = registrationRepository.existsById(id);
        if (!exists) throw new NotFoundException("registration not exists");
    }

    @Override
    public RegistrationEntity findById(Integer id) {

        this.existsById(id);
        return registrationRepository.findById(id).orElse(new RegistrationEntity());
    }

    @Override
    public BaseDataList<RegistrationEntity> findAll(RegistrationSearch search) {

        if (search.getCustomerId() == null) search.setCustomerId(0);
        if (search.getTimestamp() == null) search.setTimestamp(" ");
        if (search.getPage() == null || search.getPage() <= 0) search.setPage(1);
        if (search.getSize() == null || search.getSize() <= 0) search.setSize(10);
        Page<RegistrationEntity> entityPage = registrationRepository.findAll(new RegistrationSpecification(search), PageRequest.of(search.getPage()-1, search.getSize()));
        return BaseDataList.<RegistrationEntity>builder()
                .pagination(new BasePagination(search.getPage(), search.getSize(), entityPage.getTotalElements(), entityPage.getTotalPages()))
                .data(entityPage.getContent())
                .build();
    }

    @Transactional
    @Override
    public List<RegistrationEntity> register(RegistrationDTO dto) {

        List<RegistrationEntity> entities = new ArrayList<>();
        //insert customer
        CustomerEntity customer = customerRepository.save(customerMapper.from(dto.getCustomer()));
        //check course, promotion and add to registration list
        Set<Integer> promotionIds = new HashSet<>();
        for (Integer courseId : dto.getCourseId()){
            //check course
            CourseEntity course = courseService.findById(courseId);
            //check promotion
            if (Objects.nonNull(dto.getPromotion()) && Objects.nonNull(dto.getPromotion().getId())){
                PromotionEntity promotion = promotionService.findById(dto.getPromotion().getId());
                promotionIds.add(dto.getPromotion().getId());
                //get promotion id from each course
                if (Objects.nonNull(course.getPromotionEntity()) && Objects.nonNull(course.getPromotionEntity().getId())){
                    promotionIds.add(course.getPromotionEntity().getId());
                }else {
                    throw new BadRequestException("please check promotion again");
                }
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
        //check all the course must be the same promotion and equal promotion request
        if (promotionIds.size() > 1){
            throw new BadRequestException("please check promotion again");
        }
        return registrationRepository.saveAll(entities);
    }


}
