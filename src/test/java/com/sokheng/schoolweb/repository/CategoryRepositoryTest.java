package com.sokheng.schoolweb.repository;

import com.sokheng.schoolweb.entity.CategoryEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Transactional
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void init(){

        //given
        CategoryEntity category = new CategoryEntity();
        category.setId(1);
        category.setName("Test Category");
        category.setDeleted(false);
        categoryRepository.save(category);
    }

    @Test
    public void updateStatusById(){

        // when
        categoryRepository.updateStatusById(1);
        // Flush and clear the entity manager to ensure the update is committed and fetched from the database
        entityManager.flush();
        entityManager.clear();
        // then
        CategoryEntity updatedCategory = categoryRepository.findById(1).orElseThrow();
        assertThat(updatedCategory.isDeleted()).isTrue();
    }

    @Test
    public void testExistsByIdAndIsDeletedFalse(){

        //when
        boolean exists = categoryRepository.existsByIdAndIsDeletedFalse(1);
        //then
        assertTrue(exists);
    }
}
