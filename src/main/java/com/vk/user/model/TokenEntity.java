package com.vk.user.model;

import com.vk.user.constant.ECommonStatus;
import com.vk.user.constant.ETokenType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account")
public class TokenEntity {

    @Id
    @UuidGenerator
    private String token;

    @Column(name = "type")
    private ETokenType type;

    @Column(name = "expire_at")
    private LocalDateTime expireAt;

    @Column(name = "status")
    private ECommonStatus status;
}
