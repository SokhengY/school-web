package com.sokheng.schoolweb.utils;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseList<T> {

    private Integer code;

    private String message;

    private BasePagination pagination;

    private T data;

    public BaseResponseList(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
