package com.vk.user.config.security;

import com.google.gson.Gson;
import com.vk.user.constant.ErrorCode;
import com.vk.user.service.dto.res.BaseResponse;
import com.vk.user.util.TranslatorUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

@Log4j2
@Component
@RequiredArgsConstructor
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private final TranslatorUtil translatorUtil;
    private final Gson gson;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
         BaseResponse body = BaseResponse.builder()
                .meta(BaseResponse.Meta.builder()
                        .code(ErrorCode.UNAUTHORIZED.getValue())
                        .message(translatorUtil.getMessage(ErrorCode.UNAUTHORIZED.getValue()))
                        .build())
                 .data(null)
                .build();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(this.gson.toJson(body));
        out.flush();
    }
}
