package com.vk.user.controller.adivce;


import com.vk.user.constant.ErrorCode;
import com.vk.user.exception.BusinessException;
import com.vk.user.service.dto.res.BaseResponse;
import com.vk.user.util.TranslatorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.BindException;


@Log4j2
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    private final TranslatorUtil translatorUtil;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        String message = null;
        if (!ex.getBindingResult().getAllErrors().isEmpty()){
            message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponse.builder()
                .meta(BaseResponse.Meta.builder()
                        .code(ErrorCode.BAD_REQUEST.getValue())
                        .message(message)
                        .build())
                .build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse.builder()
                .meta(BaseResponse.Meta.builder()
                        .code(ErrorCode.UNAUTHORIZED.getValue())
                        .message(translatorUtil.getMessage(ErrorCode.UNAUTHORIZED.getValue()))
                        .build())
                .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(BaseResponse.builder()
                .meta(BaseResponse.Meta.builder()
                        .code(ErrorCode.ACCESS_DENIED.getValue())
                        .message(translatorUtil.getMessage(ErrorCode.ACCESS_DENIED.getValue()))
                        .build())
                .build());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.builder()
                .meta(BaseResponse.Meta.builder()
                        .code(ex.getCode())
                        .message(translatorUtil.getMessage(ex.getCode()))
                        .build())
                .build());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleCustomException(Throwable throwable, WebRequest request) {
        log.error(throwable.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.builder()
                .meta(BaseResponse.Meta.builder()
                        .code(ErrorCode.SERVER_ERROR.getValue())
                        .message(translatorUtil.getMessage(ErrorCode.SERVER_ERROR.getValue()))
                        .build())
                .build());
    }
}
