package com.sokheng.schoolweb.dto.category_dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Integer id;

    private String name;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private boolean isDeleted;

}
