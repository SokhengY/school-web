package com.sokheng.schoolweb.mapper;

import com.sokheng.schoolweb.dto.registration_dto.RegistrationDTO;
import com.sokheng.schoolweb.dto.registration_dto.RegistrationRequest;
import com.sokheng.schoolweb.dto.registration_dto.RegistrationResponse;
import com.sokheng.schoolweb.entity.RegistrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    List<RegistrationResponse> from(List<RegistrationEntity> entities);

    @Mapping(source = "courseEntity", target = "course")
    @Mapping(source = "customerEntity", target = "customer")
    @Mapping(source = "promotionEntity", target = "promotion")
    RegistrationResponse from(RegistrationEntity entity);

    @Mapping(source = "customer.dob", target = "customer.dob", qualifiedByName = "stringToTimestamp")
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "promotionId", target = "promotion.id")
    RegistrationDTO from(RegistrationRequest request);

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
