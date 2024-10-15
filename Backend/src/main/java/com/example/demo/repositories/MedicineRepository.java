package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repositories.entities.Category;
import com.example.demo.repositories.entities.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long>{
	List<Medicine> findAllByIsDeletedFalse();
	Medicine findByIdAndIsDeletedFalse(long id);
	List<Medicine> findByCategoryAndIsDeletedFalse(Category category);
}
