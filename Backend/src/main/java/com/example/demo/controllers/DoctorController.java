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


import com.example.demo.repositories.entities.Doctor;
import com.example.demo.request.CreateDoctorRequest;
import com.example.demo.request.UpdateDoctorRequest;
import com.example.demo.response.CreateDoctorResponse;
import com.example.demo.response.UpdateDoctorResponse;
import com.example.demo.service.DoctorService;



@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	private final DoctorService service;
	
	public DoctorController(DoctorService Doctorservice) {
		this.service = Doctorservice;
	}
	
	@GetMapping("/{id}")
	public Doctor getbyId(@PathVariable long id) {
		return service.getById(id);
	}
	
	
	@GetMapping
	public List<Doctor> getAll() {
		return service.getAll();
	
	}
	
	@PostMapping
    public ResponseEntity<CreateDoctorResponse> createDoctor(@RequestBody CreateDoctorRequest doctorDTO) {
        CreateDoctorResponse createdDoctorDTO = service.create(doctorDTO);
        return ResponseEntity.ok(createdDoctorDTO);
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<UpdateDoctorResponse> updateDoctor(@PathVariable Long id, @RequestBody UpdateDoctorRequest doctorDTO){
		UpdateDoctorResponse updatedDoctor = service.update(id,doctorDTO);
		return ResponseEntity.ok(updatedDoctor);
	}
	
	@DeleteMapping("/{id}")
	public void deleteDoctor(@PathVariable long id) {
		service.delete(id);
	}
}
