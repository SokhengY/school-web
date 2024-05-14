package com.sokheng.schoolweb.service.impl;

import com.sokheng.schoolweb.dto.payment_dto.PaymentDetailRequest;
import com.sokheng.schoolweb.dto.payment_dto.PaymentRequest;
import com.sokheng.schoolweb.entity.*;
import com.sokheng.schoolweb.exception.BadRequestException;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.repository.CoursePaymentRepository;
import com.sokheng.schoolweb.repository.PaymentDetailRepository;
import com.sokheng.schoolweb.repository.PaymentRepository;
import com.sokheng.schoolweb.repository.RegistrationRepository;
import com.sokheng.schoolweb.service.interfaces.CourseService;
import com.sokheng.schoolweb.service.interfaces.CustomerService;
import com.sokheng.schoolweb.service.interfaces.PaymentService;
import com.sokheng.schoolweb.service.interfaces.PromotionService;
import com.sokheng.schoolweb.utils.BaseDataList;
import com.sokheng.schoolweb.utils.BasePagination;
import com.sokheng.schoolweb.utils.common_enum.OccupationEnum;
import com.sokheng.schoolweb.utils.common_enum.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentDetailRepository paymentDetailRepository;
    private final CoursePaymentRepository coursePaymentRepository;
    private final RegistrationRepository registrationRepository;
    private final CustomerService customerService;
    private final CourseService courseService;
    private final PromotionService promotionService;

    @Override
    public PaymentDetailEntity createPaymentDetail(PaymentDetailRequest request) {

        //check payment
        this.existsById(request.getPaymentId());
        //check payment in payment detail
        Optional<PaymentDetailEntity> paymentDetail = paymentDetailRepository.findByPaymentId(request.getPaymentId());
        if (paymentDetail.isEmpty()) throw new NotFoundException("payment detail not exists");
        PaymentDetailEntity paymentDetailEntity = paymentDetail.get();
        //save to payment detail
        PaymentDetailEntity entity = new PaymentDetailEntity();
        entity.setPaymentEntity(paymentDetailEntity.getPaymentEntity());
        entity.setTotalAmount(paymentDetailEntity.getTotalAmount());
        entity.setAmountPaid(request.getAmountPaid());
        entity.setCurrency(request.getCurrency());
        PaymentDetailEntity saved = paymentDetailRepository.save(entity);

        return saved;
    }

    public void existsById(Integer id){

        boolean exists = paymentRepository.existsById(id);
        if (!exists) throw new BadRequestException("payment not exists");
    }

    @Override
    public BaseDataList<PaymentEntity> findAll(Integer page, Integer size) {

        if (page <= 0) page = 1;
        if (size <= 0) size = 10;
        Page<PaymentEntity> entities = paymentRepository.findAll(PageRequest.of(page-1, size));
        return BaseDataList.<PaymentEntity>builder()
                .pagination(new BasePagination(page, size, entities.getTotalElements(), entities.getTotalPages()))
                .data(entities.getContent())
                .build();
    }

    @Transactional
    @Override
    public PaymentEntity create(PaymentRequest request) {

        //payment
        PaymentEntity paymentEntity = new PaymentEntity();
        List<CourseEntity> courseEntities = new ArrayList<>();
        //payment detail
        PaymentDetailEntity paymentDetail = new PaymentDetailEntity();
        paymentDetail.setAmountPaid(request.getAmountPaid());
        paymentDetail.setCurrency(request.getCurrency());
        Double totalAmount = 0.0;
        Set<Double> totalAmountPro = new HashSet<>();
        //check customer
        CustomerEntity customer = customerService.findById(request.getCustomerId());
        paymentEntity.setCustomerEntity(customer);
        //check course with promotion and no promotion
        for (Integer courseId : request.getCourseIds()){
            //check customer register course or not
            Optional<RegistrationEntity> registrationEntity = registrationRepository.findByCustomerIdAndCourseId(request.getCustomerId(), courseId);
            if (registrationEntity.isPresent()){
                RegistrationEntity registration = registrationEntity.get();
                //check promotion
                if (Objects.nonNull(registration.getPromotionEntity())){
                    PromotionEntity promotion = promotionService.findById(registration.getPromotionEntity().getId());
                    totalAmountPro.add(promotion.getAmount());
                    //check course
                    if (Objects.nonNull(registration.getCourseEntity())){
                        CourseEntity course = courseService.findById(registration.getCourseEntity().getId());
                        courseEntities.add(course);
                    }
                }else {
                    if (Objects.nonNull(registration.getCourseEntity())){
                        CourseEntity course = courseService.findById(registration.getCourseEntity().getId());
                        courseEntities.add(course);
                        //check customer type to set price
                        if (Objects.equals(customer.getOccupation(), OccupationEnum.EMPLOYEE) && Objects.nonNull(course.getPriceEntities())){
                            for (PriceEntity entity : course.getPriceEntities()){
                                if (Objects.equals(entity.getType(), OccupationEnum.EMPLOYEE)){
                                    totalAmount += entity.getAmount();
                                }
                            }
                        }
                        if (Objects.equals(customer.getOccupation(), OccupationEnum.STUDENT) && Objects.nonNull(course.getPriceEntities())){
                            for (PriceEntity entity : course.getPriceEntities()){
                                if (Objects.equals(entity.getType(), OccupationEnum.STUDENT)){
                                    totalAmount += entity.getAmount();
                                }
                            }
                        }
                    }
                }
            }
        }
        //set total amount
        Optional<Double> a = totalAmountPro.stream().findFirst();
        Double b = 0.0;
        if (a.isPresent()){
            b = a.get();
        }
        double c = totalAmount + b;
        paymentDetail.setTotalAmount(c);
        //save payment first
        if (request.getAmountPaid() < c){
            paymentEntity.setStatus(StatusEnum.PAID_IN_PROGRESS);
        }else {
            paymentEntity.setStatus(StatusEnum.PAID);
        }
        PaymentEntity savePayment = paymentRepository.save(paymentEntity);
        //save payment detail
        paymentDetail.setPaymentEntity(savePayment);
        paymentDetailRepository.save(paymentDetail);
        //save course_payment
        List<CoursePaymentEntity> list = new ArrayList<>();
        if (courseEntities.size() != 0){
            for (CourseEntity course : courseEntities){
                CoursePaymentEntity entity = new CoursePaymentEntity();
                entity.setCourseEntity(course);
                entity.setPaymentEntity(savePayment);
                list.add(entity);
            }
        }
        coursePaymentRepository.saveAll(list);

        return savePayment;
    }
}
