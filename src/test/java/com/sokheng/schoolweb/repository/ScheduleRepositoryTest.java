package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.ScheduleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Transactional
@DataJpaTest
public class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void init(){

        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setDeleted(false);
        scheduleRepository.save(schedule);
    }

    @Test
    public void testUpdateStatusById(){

        //when
        scheduleRepository.updateStatusById(1);

        entityManager.flush();
        entityManager.clear();
        //then
        ScheduleEntity schedule = scheduleRepository.findById(1).get();
        assertThat(schedule.isDeleted()).isTrue();
    }

    @Test
    public void testExistsByIdAndIsDeletedFalse(){

        //given
        Integer id = 1;
        //when
        boolean exists = scheduleRepository.existsByIdAndIsDeletedFalse(id);
        //then
        assertTrue(!exists);
    }
}
