package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.CategoryEntity;
import com.sokheng.schoolweb.entity.PromotionEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Transactional
@DataJpaTest
public class PromotionRepositoryTest {

    @Autowired
    public PromotionRepository promotionRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void init(){

        PromotionEntity entity = new PromotionEntity();
        entity.setRequirement("testing");
        entity.setDeleted(false);
        promotionRepository.save(entity);
    }

    @Test
    @DirtiesContext
    public void testUpdateStatusById(){

        // when
        promotionRepository.updateStatusById(1);
        // Flush and clear the entity manager to ensure the update is committed and fetched from the database
        entityManager.flush();
        entityManager.clear();
        // then
        PromotionEntity updatePromotion = promotionRepository.findById(1).orElseThrow();
        assertThat(updatePromotion.isDeleted()).isTrue();
    }

    @Test
    public void testExistsByIdAndIsDeletedFalse(){

        //given
        Integer id = 1;
        //when
        boolean exists = promotionRepository.existsByIdAndIsDeletedFalse(id);
        //then
        assertTrue(exists);
    }
}
