package com.petsociety.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petsociety.backend.entity.ApplicationEntity;


@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Integer> {
}
