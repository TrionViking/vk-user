package com.vk.user.controller;


import com.vk.user.constant.ErrorCode;
import com.vk.user.service.dto.res.BaseResponse;
import com.vk.user.util.TranslatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    @Autowired
    private TranslatorUtil translatorUtil;

    protected ResponseEntity<BaseResponse> success(Object data) {
        BaseResponse resData = BaseResponse.builder()
                .meta(BaseResponse.Meta.builder()
                        .code(ErrorCode.SUCCESS.getValue())
                        .message(translatorUtil.getMessage(ErrorCode.SUCCESS.getValue()))
                        .build())
                .data(data)
                .build();
        return new ResponseEntity(resData, HttpStatus.OK);
    }

    protected ResponseEntity<BaseResponse> badRequest() {
        BaseResponse resData = BaseResponse.builder()
                .meta(BaseResponse.Meta.builder()
                        .code(ErrorCode.BAD_REQUEST.getValue())
                        .message(translatorUtil.getMessage(ErrorCode.BAD_REQUEST.getValue()))
                        .build())
                .build();
        return new ResponseEntity(resData, HttpStatus.BAD_REQUEST);
    }

}
