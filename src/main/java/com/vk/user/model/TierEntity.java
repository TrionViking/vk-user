package com.vk.user.model;

import com.vk.user.constant.ECommonStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tier")
public class TierEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private Boolean isDefault;

    @Column
    private String url;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ECommonStatus status;
}
