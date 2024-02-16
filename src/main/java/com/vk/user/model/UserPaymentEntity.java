package com.vk.user.model;

import com.vk.user.constant.ECommonStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {
        @Index(columnList = "userId")
})
public class UserPaymentEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column
    private Long userId;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ECommonStatus status;
}
