package com.sokheng.schoolweb.service;

import com.sokheng.schoolweb.dto.course_dto.price_dto.PriceRequest;
import com.sokheng.schoolweb.dto.promotion_dto.PromotionRequest;
import com.sokheng.schoolweb.entity.PriceEntity;
import com.sokheng.schoolweb.entity.PromotionEntity;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.repository.PriceRepository;
import com.sokheng.schoolweb.service.impl.PriceServiceImpl;
import com.sokheng.schoolweb.utils.common_enum.CurrencyEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceServiceImpl;

    @Test
    public void testUpdatePrice(){

        // given
        Integer id = 1;
        PriceEntity price = new PriceEntity();
        price.setId(id);
        price.setCurrency(CurrencyEnum.KHR);

        PriceRequest request = new PriceRequest();
        request.setCurrency(CurrencyEnum.USD);
        //when
        when(priceRepository.existsById(id)).thenReturn(true);
        when(priceRepository.findById(id)).thenReturn(Optional.of(price));
        when(priceRepository.save(any(PriceEntity.class))).thenReturn(price);
        PriceEntity result = priceServiceImpl.updatePrice(id, request);
        //then
        assertNotNull(result);
        assertEquals(request.getCurrency(), result.getCurrency());
        verify(priceRepository, times(1)).existsById(id);
        verify(priceRepository, times(1)).findById(id);
        verify(priceRepository, times(1)).save(price);
    }

    @Test
    public void testFindById(){

        //given
        Integer id = 1;
        PriceEntity price = new PriceEntity();
        price.setId(id);
        price.setCurrency(CurrencyEnum.KHR);
        //when
        when(priceRepository.existsById(id)).thenReturn(true);
        when(priceRepository.findById(id)).thenReturn(Optional.of(price));
        PriceEntity result = priceServiceImpl.findById(id);
        //then
        assertThat(result.getId()).isEqualTo(id);
        verify(priceRepository, times(1)).existsById(id);
        verify(priceRepository, times(1)).findById(id);
    }

    @Test
    public void testExistsByIdError(){

        // when
        Integer id = 1;
        when(priceRepository.existsById(id)).thenReturn(false);
        assertThrows(NotFoundException.class, () -> priceServiceImpl.existsById(id), "price not exists");
        //then
        verify(priceRepository, times(1)).existsById(id);
    }
}
