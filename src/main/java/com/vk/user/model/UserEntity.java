package com.vk.user.model;

import com.vk.user.constant.ECommonStatus;
import com.vk.user.constant.EIdType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private Boolean sex;

    @Column
    private String avatar;

    @Column
    private LocalDate dob;

    @Column
    private Integer tierId;

    @Column
    private LocalDateTime registerDate;

    @Column
    @Enumerated(value = EnumType.STRING)
    private EIdType idType;

    @Column
    private String idNo;
}
