package com.vk.user.model;

import com.vk.user.constant.ECommonStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @Column(name = "updated_by")
    private String updateBy;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private ECommonStatus status;
}
