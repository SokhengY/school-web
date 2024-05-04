package com.sokheng.schoolweb.mapper;

import com.sokheng.schoolweb.dto.course_dto.price_dto.PriceDTO;
import com.sokheng.schoolweb.dto.course_dto.price_dto.PriceRequest;
import com.sokheng.schoolweb.entity.PriceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    List<PriceEntity> fromListDTO(List<PriceDTO> dtos);

    List<PriceDTO> from(List<PriceEntity> list);

    PriceDTO from(PriceRequest request);

    PriceEntity from(PriceDTO dto);

    PriceDTO from(PriceEntity entity);
}
