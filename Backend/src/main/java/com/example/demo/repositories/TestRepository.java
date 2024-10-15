package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repositories.entities.Test;

public interface TestRepository extends JpaRepository<Test, Long>{
	List<Test> findAllByIsDeletedFalse();
	Test findAllByIdAndIsDeletedFalse(long id);
}
