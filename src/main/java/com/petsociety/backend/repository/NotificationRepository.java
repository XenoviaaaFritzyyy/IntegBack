package com.petsociety.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petsociety.backend.entity.NotificationEntity;
import com.petsociety.backend.entity.UserEntity;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findByUserOrderByTimestampDesc(UserEntity user);

    // You can add more custom query methods if needed
}

