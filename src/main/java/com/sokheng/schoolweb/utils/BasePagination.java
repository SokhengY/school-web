package com.sokheng.schoolweb.utils;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BasePagination {

    private Integer page;

    private Integer size;

    private Long totalItems;

    private Integer totalPages;


}
