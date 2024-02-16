package com.vk.user.service.dto.res;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRes {

    private String token;


}
