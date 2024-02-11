package com.vk.user.repository;

import com.vk.user.model.UserPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentRepository extends JpaRepository<UserPaymentEntity, String> {
}
