package com.vk.user.service.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SigninReq {


    private String username;


    private String password;

}
