package com.vk.user.service.dto.res;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BaseResponse<D> {

    private Meta meta;
    private D data;

    @Data
    @SuperBuilder
    public static class Meta{
        private Integer code;
        private String message;
    }

}
