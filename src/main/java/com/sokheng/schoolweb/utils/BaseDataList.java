package com.sokheng.schoolweb.utils;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseDataList<T> {

    private List<T> data;

    private BasePagination pagination;
}
