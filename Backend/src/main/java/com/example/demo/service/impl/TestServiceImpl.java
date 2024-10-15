package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.demo.repositories.TestRepository;
import com.example.demo.repositories.entities.Test;
import com.example.demo.request.CreateTestRequest;
import com.example.demo.request.UpdateTestRequest;
import com.example.demo.response.CreateTestResponse;
import com.example.demo.response.UpdateTestResponse;
import com.example.demo.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	private final TestRepository testrepo;
	public TestServiceImpl(TestRepository testrepo) {
		this.testrepo = testrepo;
	}
	
	@Override
	public Test getById(long id) {
		Test test = testrepo.getById(id);
		return test;
	}
	
	
	@Override
	public List<Test> getAll() {
		return testrepo.findAllByIsDeletedFalse();
	}


	@Override
	public CreateTestResponse create(CreateTestRequest testDTO) {
		Test test = Test.fromDTO(testDTO);
		Test savedtest = testrepo.save(test);
		return Test.toDTO(savedtest);
	}

	@Override
	public UpdateTestResponse update(long id, UpdateTestRequest testDTO) {
		Test test = testrepo.findById(id).orElseThrow(()->new IllegalArgumentException("Test not found."));
		test.UpdatefromDTO(testDTO);
		return Test.UpdatetoDTO(test);
	}

	@Override
	public void delete(long id) {
		Test test = testrepo.findById(id).orElseThrow(()->new IllegalArgumentException("Test not found."));
		test.setDeleted(true);
		testrepo.save(test);
	}


}
