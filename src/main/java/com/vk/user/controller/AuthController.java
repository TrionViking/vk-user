package com.vk.user.controller;

import com.vk.user.service.AuthService;
import com.vk.user.service.dto.req.SigninReq;
import com.vk.user.service.dto.req.SignupReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController extends BaseController{

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity sigin(@Validated @RequestBody SigninReq req) {
        return success(authService.signin(req));
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@Validated @RequestBody SignupReq req) {
        return success(authService.siginup(req));
    }


    @PostMapping("/u")
    public ResponseEntity u() {
        return success("user");
    }

    @PostMapping("/s")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SHOP')")
    public ResponseEntity s() {
        return success("shop");
    }

    @PostMapping("/a")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity a() {
        return success("admin");
    }

}
