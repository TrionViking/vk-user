package com.vk.user.service.impl;

import com.vk.user.model.AccountEntity;
import com.vk.user.repository.AccountRepository;
import com.vk.user.repository.AdminRepository;
import com.vk.user.repository.UserRepository;
import com.vk.user.service.AuthService;
import com.vk.user.service.JwtService;
import com.vk.user.service.dto.AuthUser;
import com.vk.user.service.dto.req.SigninReq;
import com.vk.user.service.dto.req.SignupReq;
import com.vk.user.service.dto.res.AuthenticationRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Override
    public AuthenticationRes siginup(SignupReq req) {
        AccountEntity account = AccountEntity.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .email(req.getUsername())
                .isUser(Boolean.TRUE)
                .userId(1L)
                .roleId(1)
                .build();
        accountRepository.save(account);
        AuthUser authUser = AuthUser.builder().username(req.getUsername()).build();
        String jwt = jwtService.generateToken(authUser);
        return AuthenticationRes.builder()
                .token(jwt)
                .build();
    }

    @Override
    public AuthenticationRes signin(SigninReq req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        String jwt = jwtService.generateToken(authUser);

        return AuthenticationRes.builder()
                .token(jwt)
                .build();
    }
}
