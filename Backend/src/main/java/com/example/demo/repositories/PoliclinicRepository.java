package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repositories.entities.Doctor;
import com.example.demo.repositories.entities.Policlinic;

public interface PoliclinicRepository extends JpaRepository<Policlinic, Long> {
    Policlinic findByIdAndIsDeletedFalse(long id);
    List<Policlinic> findAllByIsDeletedFalse();
    }
  