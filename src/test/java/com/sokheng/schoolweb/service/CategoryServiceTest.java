package com.sokheng.schoolweb.service;

import com.sokheng.schoolweb.dto.category_dto.CategoryDTO;
import com.sokheng.schoolweb.entity.CategoryEntity;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.repository.CategoryRepository;
import com.sokheng.schoolweb.service.impl.CategoryServiceImpl;
import com.sokheng.schoolweb.utils.BaseDataList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @Test
    public void testUpdate(){

        //given
        Integer id = 1;
        CategoryEntity existingCategory = new CategoryEntity();
        existingCategory.setId(id);
        existingCategory.setName("Create Category");
        existingCategory.setDeleted(false);
        categoryRepository.save(existingCategory);

        CategoryDTO dto = new CategoryDTO();
        dto.setName("Updated Category");

        CategoryEntity updatedCategory = new CategoryEntity();
        updatedCategory.setId(id);
        updatedCategory.setName("Updated Category");
        updatedCategory.setDeleted(false);
        //when
        when(categoryRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(true);
        when(categoryRepository.findById(id)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(updatedCategory);
        CategoryEntity result = categoryServiceImpl.update(id, dto);
        //then
        assertNotNull(result);
        assertEquals("Updated Category", result.getName());
        verify(categoryRepository, times(1)).existsByIdAndIsDeletedFalse(id);
        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, times(2)).save(any(CategoryEntity.class));
    }

    @Test
    public void testSoftDelete(){

        //given
        Integer id = 1;
        //when
        when(categoryRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(true);
        categoryServiceImpl.softDelete(id);
        //then
        verify(categoryRepository, times(1)).existsByIdAndIsDeletedFalse(id);
        verify(categoryRepository, times(1)).updateStatusById(id);
    }

    @Test
    public void testFindAll() {

        // given
        Integer page = 1;
        Integer size = 10;

        CategoryEntity category1 = new CategoryEntity();
        category1.setId(1);
        category1.setDeleted(false);

        CategoryEntity category2 = new CategoryEntity();
        category2.setId(2);
        category2.setDeleted(false);

        List<CategoryEntity> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        Page<CategoryEntity> categoryPage = new PageImpl<>(categories, PageRequest.of(0, size), categories.size());

        //when
        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(categoryPage);
        BaseDataList<CategoryEntity> result = categoryServiceImpl.findAll(page, size);

        // then
        assertNotNull(result);
        assertEquals(2, result.getData().size());
        assertEquals(page, result.getPagination().getPage());
        assertEquals(size, result.getPagination().getSize());
        assertEquals(categories.size(), result.getPagination().getTotalItems());
        assertEquals(1, result.getPagination().getTotalPages());

        verify(categoryRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void testExistsByIdAndStatusError() {

        // when
        Integer id = 1;
        when(categoryRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(false);
        assertThrows(NotFoundException.class, () -> categoryServiceImpl.existsByIdAndStatus(id), "category not exists!");
        //then
        verify(categoryRepository, times(1)).existsByIdAndIsDeletedFalse(id);
    }

    @Test
    public void testFindById(){

        //given
        CategoryEntity category = new CategoryEntity();
        category.setId(1);
        category.setName("Test Category");
        category.setDeleted(false);
        //when
        when(categoryRepository.existsByIdAndIsDeletedFalse(1)).thenReturn(true);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        CategoryEntity categoryReturn = categoryServiceImpl.findById(1);
        //then
        assertEquals(1, categoryReturn.getId());
    }

    @Test
    public void testCreate(){

        //given
        CategoryEntity category = new CategoryEntity();
        category.setName("Test Category");
        category.setDeleted(false);
        //when
        categoryServiceImpl.create(category);
        //then
        verify(categoryRepository, times(1)).save(category);
    }
}
