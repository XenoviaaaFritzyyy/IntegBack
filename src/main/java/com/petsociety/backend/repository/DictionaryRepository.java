package com.petsociety.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petsociety.backend.entity.DictionaryEntity;

@Repository
public interface DictionaryRepository extends JpaRepository<DictionaryEntity, Integer>{

}
