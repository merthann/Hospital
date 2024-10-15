package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.repositories.entities.Doctor;


import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByIdAndIsDeletedFalse(long id);
    List<Doctor> findAllByIsDeletedFalse();
    List<Doctor> findByPoliclinicIdAndIsDeletedFalse(long policlinicid);
}