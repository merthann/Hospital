package com.example.demo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repositories.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
	List<Patient> findAllByIsDeletedFalse();
	Patient findByIdAndIsDeletedFalse(UUID id);
	Patient findByIdentitynoAndIsDeletedFalse(long identityno);
}
