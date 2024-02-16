package com.vk.user.service.impl;

import com.vk.user.constant.*;
import com.vk.user.exception.BusinessException;
import com.vk.user.model.*;
import com.vk.user.repository.*;
import com.vk.user.service.AuthService;
import com.vk.user.service.JwtService;
import com.vk.user.service.dto.AuthUser;
import com.vk.user.service.dto.req.SigninReq;
import com.vk.user.service.dto.req.SignupReq;
import com.vk.user.service.dto.res.AuthenticationRes;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper mapper;

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    private final UserPaymentRepository userPaymentRepository;
    private final RoleRepository roleRepository;
    private final TierRepository tierRepository;

    @Override
    @Transactional
    public AuthenticationRes siginup(SignupReq req) {
        if (accountRepository.existsByUsername(req.getUsername())) {
            throw new BusinessException(ErrorCode.USERNAME_EXIST);
        }
        if (accountRepository.existsByEmail(req.getEmail())) {
            throw new BusinessException(ErrorCode.EMAIL_EXIST);
        }
        RoleEntity role = roleRepository.findByCodeAndStatus(ERole.ROLE_USER.name(), ECommonStatus.ACTIVE)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND));
        UserEntity user = mapper.map(req, UserEntity.class);
        user = setUserDefaultSettingAndSave(user);
        saveAddress(req, user);
        savePayment(req, user);
        saveAccount(req, user, role);

        AuthUser authUser = AuthUser.builder().username(req.getUsername()).build();
        String jwt = jwtService.generateToken(authUser);
        return AuthenticationRes.builder()
                .token(jwt)
                .roles(List.of(role.getCode()))
                .build();
    }

    @Override
    public AuthenticationRes signin(SigninReq req) {
        Authentication authentication;
        try {
             authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        } catch (Exception ex) {
            throw new BadCredentialsException("Authentication was failed");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        String jwt = jwtService.generateToken(authUser);

        return AuthenticationRes.builder()
                .token(jwt)
                .build();
    }

    private AccountEntity saveAccount(SignupReq req, UserEntity user, RoleEntity role) {
        AccountEntity account = AccountEntity.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .email(req.getEmail())
                .type(EAccountType.LOCAL)
                .userId(user.getId())
                .isUser(Boolean.TRUE)
                .status(EAccountStatus.ACTIVE)
                .roleId(role.getId())
                .build();
        return accountRepository.save(account);
    }

    private UserEntity setUserDefaultSettingAndSave(UserEntity user) {
        user.setRegisterDate(LocalDateTime.now());
        tierRepository.findByStatus(ECommonStatus.ACTIVE)
                .stream()
                .filter(tier -> Boolean.TRUE.equals(tier.getIsDefault()))
                .reduce((a, b) -> a)
                .ifPresent(tier -> user.setTierId(tier.getId()));
        return userRepository.save(user);
    }


    private void saveAddress(SignupReq req, UserEntity user) {
        if (Objects.isNull(req.getAddress())) {
            return;
        }
        UserAddressEntity userAddress = mapper.map(req.getAddress(), UserAddressEntity.class);
        userAddress.setStatus(ECommonStatus.ACTIVE);
        userAddress.setUserId(user.getId());
        userAddressRepository.save(userAddress);
    }

    private void savePayment(SignupReq req, UserEntity user) {
        if (Objects.isNull(req.getPayment())) {
            return;
        }
        UserPaymentEntity userPayment = mapper.map(req.getAddress(), UserPaymentEntity.class);
        userPayment.setStatus(ECommonStatus.ACTIVE);
        userPayment.setUserId(user.getId());
        userPaymentRepository.save(userPayment);
    }
}
