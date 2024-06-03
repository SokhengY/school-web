package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.CourseEntity;
import com.sokheng.schoolweb.entity.CoursePaymentEntity;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Transactional
@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void init(){

        //given
        PromotionEntity promotion = new PromotionEntity();
        promotion.setId(1);
        promotionRepository.save(promotion);
        Integer id = 1;
        CourseEntity course = new CourseEntity();
        course.setId(id);
        course.setPromotionEntity(promotion);
        course.setName("test course");
        course.setDeleted(false);
        courseRepository.save(course);
    }

    @DirtiesContext
    @Test
    public void testFindAllByPromotionId(){

        //given
        PromotionEntity promotion = new PromotionEntity();
        promotion.setId(2);
        promotionRepository.save(promotion);

        CourseEntity course1 = new CourseEntity();
        course1.setId(1);
        course1.setPromotionEntity(promotion);
        courseRepository.save(course1);

        CourseEntity course2 = new CourseEntity();
        course2.setId(2);
        course2.setPromotionEntity(promotion);
        courseRepository.save(course2);
        //when
        List<Integer> courseIds = courseRepository.findAllByPromotionId(2);
        //then
        assertThat(courseIds).containsExactlyInAnyOrder(1, 2);
    }

    @DirtiesContext
    @Test
    public void testDeletePromotionId() {

        // given
        Integer id = 1;
        // when
        courseRepository.deletePromotionId(1);
        entityManager.flush();
        entityManager.clear();
        // then
        CourseEntity updatedCourse = courseRepository.findById(1).orElse(null);
        assertThat(updatedCourse).isNotNull();
        assertThat(updatedCourse.getPromotionEntity()).isNull();
    }

    @DirtiesContext
    @Test
    public void testUpdateStatusById(){

        //given
        Integer id = 1;
        //when
        courseRepository.updateStatusById(id);
        // Flush and clear the entity manager to ensure the update is committed and fetched from the database
        entityManager.flush();
        entityManager.clear();
        //then
        CourseEntity course = courseRepository.findById(id).orElseThrow();
        assertThat(course.isDeleted()).isTrue();
    }

    @DirtiesContext
    @Test
    public void testExistsByIdAndIsDeletedFalse(){

        //given
        Integer id = 1;
        //when
        boolean exists = courseRepository.existsByIdAndIsDeletedFalse(id);
        //then
        assertTrue(exists);
    }
}
