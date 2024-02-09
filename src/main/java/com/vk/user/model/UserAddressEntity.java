package com.vk.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_address")
public class UserAddressEntity extends BaseEntity{

    @Id
    private String id;

    @Column
    private String provinceCode;

    @Column
    private String districtCode;

    @Column
    private String wardCode;

    @Column
    private String street;

    @Column
    private Long userId;
}
