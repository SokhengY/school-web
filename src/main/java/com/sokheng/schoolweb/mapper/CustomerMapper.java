package com.sokheng.schoolweb.mapper;

import com.sokheng.schoolweb.dto.category_dto.CategoryDTO;
import com.sokheng.schoolweb.dto.course_dto.CourseDTO;
import com.sokheng.schoolweb.dto.course_dto.price_dto.PriceDTO;
import com.sokheng.schoolweb.dto.course_dto.schedule_dto.ScheduleDTO;
import com.sokheng.schoolweb.dto.promotion_dto.PromotionDTO;
import com.sokheng.schoolweb.dto.registration_dto.customer_dto.CustomerDTO;
import com.sokheng.schoolweb.dto.registration_dto.customer_dto.CustomerRequest;
import com.sokheng.schoolweb.entity.CourseEntity;
import com.sokheng.schoolweb.entity.CustomerEntity;
import com.sokheng.schoolweb.entity.PriceEntity;
import com.sokheng.schoolweb.entity.ScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    List<CustomerDTO> from(List<CustomerEntity> list);

    @Mapping(target = "course", expression = "java(mapCourse(customerEntity))")
    CustomerDTO from(CustomerEntity customerEntity);

    default List<CourseDTO> mapCourse(CustomerEntity entity){

        List<CourseDTO> dtos = new ArrayList<>();
        for (CourseEntity course : entity.getCourseEntities()){
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setName(course.getName());
            courseDTO.setDetail(course.getDetail());
            courseDTO.setStatus(course.getStatus());
            courseDTO.setCreatedAt(course.getCreatedAt());
            courseDTO.setUpdatedAt(course.getUpdatedAt());
            courseDTO.setDeleted(course.isDeleted());
            if (Objects.nonNull(course.getCategoryEntity())){
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setId(course.getCategoryEntity().getId());
                categoryDTO.setName(course.getCategoryEntity().getName());
                categoryDTO.setCreatedAt(course.getCategoryEntity().getCreatedAt());
                categoryDTO.setUpdatedAt(course.getCategoryEntity().getUpdatedAt());
                categoryDTO.setDeleted(course.getCategoryEntity().isDeleted());
                courseDTO.setCategory(categoryDTO);
            }
            if (Objects.nonNull(course.getPromotionEntity())){
                PromotionDTO promotionDTO = new PromotionDTO();
                promotionDTO.setId(course.getPromotionEntity().getId());
                promotionDTO.setRequirement(course.getPromotionEntity().getRequirement());
                promotionDTO.setAmount(course.getPromotionEntity().getAmount());
                promotionDTO.setStartDate(course.getPromotionEntity().getStartDate());
                promotionDTO.setEndDate(course.getPromotionEntity().getEndDate());
                promotionDTO.setCreatedAt(course.getPromotionEntity().getCreatedAt());
                promotionDTO.setUpdatedAt(course.getPromotionEntity().getUpdatedAt());
                promotionDTO.setDeleted(course.getPromotionEntity().isDeleted());
                courseDTO.setPromotion(promotionDTO);
            }
            if (course.getPriceEntities().size() > 0){
                List<PriceDTO> priceDTOS = new ArrayList<>();
                for (PriceEntity price : course.getPriceEntities()){
                    PriceDTO priceDTO = new PriceDTO();
                    priceDTO.setId(price.getId());
                    priceDTO.setAmount(price.getAmount());
                    priceDTO.setCurrency(price.getCurrency());
                    priceDTO.setType(price.getType());
                    priceDTOS.add(priceDTO);
                }
                courseDTO.setPrice(priceDTOS);
            }
            if (course.getScheduleEntities().size() > 0){
                List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
                for (ScheduleEntity schedule : course.getScheduleEntities()){
                    ScheduleDTO scheduleDTO = new ScheduleDTO();
                    scheduleDTO.setId(schedule.getId());
                    scheduleDTO.setFromTime(schedule.getFromTime());
                    scheduleDTO.setToTime(schedule.getToTime());
                    scheduleDTO.setDeleted(schedule.isDeleted());
                    scheduleDTOS.add(scheduleDTO);
                }
                courseDTO.setSchedule(scheduleDTOS);
            }
            dtos.add(courseDTO);
        }
        return dtos;
    }

    CustomerEntity from(CustomerDTO dto);

    @Mapping(source = "dob", target = "dob", qualifiedByName = "stringToTimestamp")
    CustomerDTO from(CustomerRequest request);

    @Named("stringToTimestamp")
    default Timestamp stringToTimestamp(String value) {

        try {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return new Timestamp(formatter.parse(value).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
