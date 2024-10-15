package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.entities.Test;
import com.example.demo.request.*;
import com.example.demo.response.CreateTestResponse;
import com.example.demo.response.UpdateTestResponse;
import com.example.demo.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

private final TestService service;
	
public TestController(TestService testService) {
	this.service = testService;
}


	@GetMapping("/{id}")
	public Test getbyId(@PathVariable long id) {
		return service.getById(id);
	}


	@GetMapping
	public List<Test> getAll() {
	return service.getAll();
	}
	

	@PostMapping
	public ResponseEntity<CreateTestResponse> addTest(@RequestBody CreateTestRequest testDTO){
		CreateTestResponse test = service.create(testDTO);
		return ResponseEntity.ok(test);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UpdateTestResponse> updateTest(@PathVariable long id ,@RequestBody UpdateTestRequest testDTO){
		UpdateTestResponse updatedtest = service.update(id, testDTO);
		return ResponseEntity.ok(updatedtest);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTest(@PathVariable long id) {
		service.delete(id);
	}
}
