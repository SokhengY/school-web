package com.sokheng.schoolweb.service.Impl;

import com.sokheng.schoolweb.dto.category_dto.CategoryDTO;
import com.sokheng.schoolweb.dto.course_dto.CourseDTO;
import com.sokheng.schoolweb.entity.CategoryEntity;
import com.sokheng.schoolweb.entity.CourseEntity;
import com.sokheng.schoolweb.exception.NotFoundException;
import com.sokheng.schoolweb.repository.CourseRepository;
import com.sokheng.schoolweb.service.impl.CategoryServiceImpl;
import com.sokheng.schoolweb.service.impl.CourseServiceImpl;
import com.sokheng.schoolweb.utils.BaseDataList;
import com.sokheng.schoolweb.utils.common_enum.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CategoryServiceImpl categoryServiceImpl;
    @InjectMocks
    private CourseServiceImpl courseServiceImpl;

    @Test
    public void testCreate(){


    }

    @Test
    public void testUpdate() {

        //given
        CategoryEntity category = new CategoryEntity();
        category.setId(1);

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(1);
        courseEntity.setName("Test Course");
        courseEntity.setDetail("Test Detail");
        courseEntity.setStatus(StatusEnum.PAID);
        courseEntity.setCategoryEntity(category);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Updated Course");
        courseDTO.setDetail("Updated Detail");
        courseDTO.setStatus(StatusEnum.PAID_IN_PROGRESS);
        courseDTO.setCategory(categoryDTO);
        //when
        when(courseRepository.existsByIdAndIsDeletedFalse(1)).thenReturn(true);
        when(courseRepository.findById(1)).thenReturn(Optional.of(courseEntity));
        when(categoryServiceImpl.findById(1)).thenReturn(category);
        when(courseRepository.save(any(CourseEntity.class))).thenReturn(courseEntity);
        CourseEntity updatedCourse = courseServiceImpl.update(1, courseDTO);
        //then
        assertThat(updatedCourse.getName()).isEqualTo("Updated Course");
        assertThat(updatedCourse.getDetail()).isEqualTo("Updated Detail");
        assertThat(updatedCourse.getUpdatedAt()).isNotNull();
        assertThat(updatedCourse.getCategoryEntity().getId()).isEqualTo(1);
    }

    @Test
    public void testFindAll(){

        // given
        Integer page = 1;
        Integer size = 10;

        CourseEntity course1 = new CourseEntity();
        course1.setId(1);

        CourseEntity course2 = new CourseEntity();
        course2.setId(2);

        List<CourseEntity> entities = new ArrayList<>();
        entities.add(course1);
        entities.add(course2);

        Page<CourseEntity> courseEntityPage = new PageImpl<>(entities, PageRequest.of(0, size), entities.size());

        //when
        when(courseRepository.findAll(any(Pageable.class))).thenReturn(courseEntityPage);
        BaseDataList<CourseEntity> result = courseServiceImpl.findAll(page, size);

        // then
        assertNotNull(result);
        assertEquals(2, result.getData().size());
        assertEquals(page, result.getPagination().getPage());
        assertEquals(size, result.getPagination().getSize());
        assertEquals(entities.size(), result.getPagination().getTotalItems());
        assertEquals(1, result.getPagination().getTotalPages());

        verify(courseRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void testDeleteById(){

        //given
        Integer id = 1;
        //when
        when(courseRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(true);
        courseServiceImpl.deleteById(id);
        //then
        verify(courseRepository, times(1)).existsByIdAndIsDeletedFalse(id);
        verify(courseRepository, times(1)).updateStatusById(id);
    }

    @Test
    public void testFindById(){

        //given
        Integer id = 1;
        CourseEntity course = new CourseEntity();
        course.setId(id);
        //when
        when(courseRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(true);
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        CourseEntity courseEntity = courseServiceImpl.findById(1);
        //then
        assertEquals(1, courseEntity.getId());
    }

    @Test
    public void testExistsByIdAndStatusError(){

        //given
        Integer id = 1;
        // when
        when(courseRepository.existsByIdAndIsDeletedFalse(id)).thenReturn(false);
        assertThrows(NotFoundException.class, () -> courseServiceImpl.existsByIdAndStatus(id), "course not exists");
        //then
        verify(courseRepository, times(1)).existsByIdAndIsDeletedFalse(id);
    }
}
