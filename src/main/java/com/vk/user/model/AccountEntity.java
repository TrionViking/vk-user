package com.vk.user.model;

import com.vk.user.constant.EAccountStatus;
import com.vk.user.constant.EAccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account")
public class AccountEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    @Enumerated(value = EnumType.STRING)
    private EAccountType type;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer roleId;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isUser;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private EAccountStatus status;
}
