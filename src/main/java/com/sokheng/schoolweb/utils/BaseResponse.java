package com.sokheng.schoolweb.utils;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    private Integer code;

    private String message;

    private T data;

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
