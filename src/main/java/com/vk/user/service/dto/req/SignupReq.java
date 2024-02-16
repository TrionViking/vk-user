package com.vk.user.service.dto.req;

import com.vk.user.constant.EIdType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SignupReq {

    @NotBlank(message = "'first_name' not blank")
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Boolean sex;

    // private avatar

    private LocalDate dob;

    @Size(min = 1, max = 50, message = "'username' must be between {min} and {max} characters")
    private String username;

    private EIdType idType;

    private String idNo;

    @Size(min = 1, max = 50, message = "'password' must be between {min} and {max} characters")
    private String password;


    @Size(min = 1, max = 50, message = "'email' must be between {min} and {max} characters")
    @Email
    private String email;

    private Address address;
    private Payment payment;

    @Data
    public static class Address {

        private String provinceCode;

        private String districtCode;

        private String wardCode;

        private String street;
    }

    @Data
    public static class Payment {

    }
}
