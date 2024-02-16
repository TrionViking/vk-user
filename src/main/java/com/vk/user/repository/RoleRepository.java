package com.vk.user.repository;

import com.vk.user.constant.ECommonStatus;
import com.vk.user.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByCodeAndStatus(String code, ECommonStatus status);

}
