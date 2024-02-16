package com.vk.user.repository;

import com.vk.user.constant.ECommonStatus;
import com.vk.user.model.TierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TierRepository extends JpaRepository<TierEntity, Integer> {

    List<TierEntity> findByStatus(ECommonStatus status);

}
