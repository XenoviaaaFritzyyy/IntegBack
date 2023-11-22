package com.petsociety.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petsociety.backend.entity.PetEntity;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Integer> {
}
