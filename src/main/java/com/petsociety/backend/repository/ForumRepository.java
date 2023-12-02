package com.petsociety.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petsociety.backend.entity.ForumEntity;

@Repository
public interface ForumRepository extends JpaRepository<ForumEntity, Integer> {

}