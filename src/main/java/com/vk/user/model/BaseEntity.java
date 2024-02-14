package com.vk.user.model;

import com.vk.user.constant.ECommonStatus;
import jakarta.persistence.*;
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

    @Version
    private Integer version;
}
