package com.example.demo.service;

import java.util.List;

import com.example.demo.repositories.entities.Test;
import com.example.demo.request.CreateTestRequest;
import com.example.demo.request.UpdateTestRequest;
import com.example.demo.response.CreateTestResponse;
import com.example.demo.response.UpdateTestResponse;

public interface TestService {
	List<Test> getAll();
	Test getById(long id);
	CreateTestResponse create(CreateTestRequest testDTO);
	UpdateTestResponse update(long id, UpdateTestRequest testDTO);
	void delete(long id);
}
