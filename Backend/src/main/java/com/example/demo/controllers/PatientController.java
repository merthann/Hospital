package com.example.demo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.entities.Patient;
import com.example.demo.request.CreatePatientRequest;
import com.example.demo.request.UpdatePatientRequest;
import com.example.demo.response.CreatePatientResponse;
import com.example.demo.response.UpdatePatientResponse;
import com.example.demo.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	private final PatientService service;
	
	public PatientController(PatientService Patientservice) {
		this.service = Patientservice;
	}
	
	
	@GetMapping("/{id}")
	public Patient getbyId( @PathVariable UUID id) {
		return service.getById(id);
	}
	
	
	@GetMapping
	public List<Patient> getall(){
		return service.getall();
	}
	
	@PostMapping
	public ResponseEntity<CreatePatientResponse> createPatient(@RequestBody CreatePatientRequest patient) {
        CreatePatientResponse createdPatient = service.create(patient);
        return ResponseEntity.ok(createdPatient);
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<UpdatePatientResponse> updatePatient(@PathVariable UUID id, @RequestBody UpdatePatientRequest patientDTO){
		UpdatePatientResponse updatedPatient = service.update(id,patientDTO);
		return ResponseEntity.ok(updatedPatient);
	}
	
	@DeleteMapping("/{id}")
	public void deletePatient(@PathVariable UUID id) {
		service.delete(id);
	}
	
}
