package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repositories.entities.Asistan;
import com.example.demo.repositories.entities.Doctor;

public interface AsistanRepository extends JpaRepository<Asistan,Long>{
	List<Asistan> findAllByIsDeletedFalse();
	Asistan findByIdAndIsDeletedFalse(long id);
	List<Asistan> findByFnameAndIsDeletedFalse(String Fname);
	//Asistan getByIdAndIsDeletedFalse(long id);
	List<Asistan> findByIdAndIsDeletedFalse(Long id);
}