package com.vk.user.service.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupReq {


    @NotBlank(message = "'username' not blank")
    @Size(min = 1, max = 50, message = "'username' must be between {min} and {max} characters")
    private String username;

    @NotBlank(message = "'password' not blank")
    @Size(min = 1, max = 50, message = "'password' must be between {min} and {max} characters")
    private String password;

}
