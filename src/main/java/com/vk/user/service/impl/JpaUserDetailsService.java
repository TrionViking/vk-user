package com.vk.user.service.impl;

import com.vk.user.model.AccountEntity;
import com.vk.user.model.RoleEntity;
import com.vk.user.repository.AccountRepository;
import com.vk.user.repository.AdminRepository;
import com.vk.user.repository.RoleRepository;
import com.vk.user.repository.UserRepository;
import com.vk.user.service.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        RoleEntity role = roleRepository.findById(account.getRoleId())
                .orElse(null);

        AuthUser authUser = new AuthUser(account, role == null ?
                Collections.emptyList() : List.of(role));
        if (Boolean.TRUE.equals(account.getIsUser())) {
            userRepository.findById(account.getUserId())
                    .ifPresent(authUser::setUserInfo);
        } else {
            adminRepository.findById(account.getUserId())
                    .ifPresent(authUser::setAdminInfo);
        }
        return authUser;
    }
}
