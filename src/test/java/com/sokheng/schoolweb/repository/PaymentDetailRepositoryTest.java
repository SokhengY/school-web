package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.PaymentDetailEntity;
import com.sokheng.schoolweb.entity.PaymentEntity;
import com.sokheng.schoolweb.utils.common_enum.StatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Transactional
@DataJpaTest
public class PaymentDetailRepositoryTest {

    @Autowired
    private PaymentDetailRepository paymentDetailRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @DirtiesContext
    @Test
    public void testFindByPaymentId(){

        //given
        PaymentEntity payment = new PaymentEntity();
        payment.setId(1);
        payment.setStatus(StatusEnum.PAID);
        paymentRepository.save(payment);
        PaymentDetailEntity detail = new PaymentDetailEntity();
        detail.setId(1);
        detail.setPaymentEntity(payment);
        paymentDetailRepository.save(detail);
        //when
        Optional<PaymentDetailEntity> paymentDetail = paymentDetailRepository.findByPaymentId(1);
        //then
        assertTrue(paymentDetail.isPresent());
        assertThat(paymentDetail.get().getId()).isEqualTo(1);
    }
}
