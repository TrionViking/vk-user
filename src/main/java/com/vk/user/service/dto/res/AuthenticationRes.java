package com.vk.user.service.dto.res;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthenticationRes {

    private String token;

    private List<String> roles;
}
