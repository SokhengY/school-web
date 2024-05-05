package com.sokheng.schoolweb.mapper;

import com.sokheng.schoolweb.dto.promotion_dto.PromotionDTO;
import com.sokheng.schoolweb.dto.promotion_dto.PromotionRequest;
import com.sokheng.schoolweb.entity.PromotionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    List<PromotionDTO> from(List<PromotionEntity> list);

    @Mapping(target = "startDate", source = "startDate", qualifiedByName = "stringToTimestamp")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "stringToTimestamp")
    PromotionDTO from(PromotionRequest request);

    PromotionDTO from(PromotionEntity entity);

    PromotionEntity from(PromotionDTO dto);

    @Named("stringToTimestamp")
    default Timestamp stringToTimestamp(String value) {

        try {
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return new Timestamp(formatter.parse(value).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
