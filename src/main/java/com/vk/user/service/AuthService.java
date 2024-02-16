package com.vk.user.service;

import com.vk.user.service.dto.req.SigninReq;
import com.vk.user.service.dto.req.SignupReq;
import com.vk.user.service.dto.res.AuthenticationRes;

public interface AuthService {

    AuthenticationRes siginup(SignupReq req);

    AuthenticationRes signin(SigninReq req);

}
