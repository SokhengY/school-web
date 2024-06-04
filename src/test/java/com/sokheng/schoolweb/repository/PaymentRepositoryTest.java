package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.PaymentEntity;
import com.sokheng.schoolweb.utils.common_enum.StatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Transactional
@DataJpaTest
public class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @DirtiesContext
    @Test
    public void testExistsById(){

        //given
        Integer id = 1;
        PaymentEntity payment = new PaymentEntity();
        payment.setId(id);
        payment.setStatus(StatusEnum.PAID);
        paymentRepository.save(payment);
        //when
        boolean exists = paymentRepository.existsById(id);
        //then
        assertTrue(exists);
    }
}
