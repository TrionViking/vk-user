package com.vk.user.constant;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCode {

    USER_NAME_NOT_FOUND(4000001),
    ROLE_NOT_FOUND(4000002);

    private static final Map<Integer, ErrorCode> mapByValue;
    private Integer value;

    static {
        mapByValue = new HashMap<>();
        for (ErrorCode e : values()) {
            mapByValue.put(e.getValue(), e);
        }
    }

    ErrorCode(Integer value) {
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }

}
