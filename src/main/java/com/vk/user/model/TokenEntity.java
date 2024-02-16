package com.vk.user.model;

import com.vk.user.constant.ECommonStatus;
import com.vk.user.constant.ETokenType;
import jakarta.persistence.*;
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
@Entity(name = "token")
@Table(indexes = {
        @Index(columnList = "userId")
})
public class TokenEntity {

    @Id
    @UuidGenerator
    private String token;

    @Column
    private Long userId;

    @Column
    private ETokenType type;

    @Column
    private LocalDateTime expireAt;

    @Column
    private ECommonStatus status;
}
