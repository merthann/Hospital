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

import com.example.demo.repositories.DoctorRepository;
import com.example.demo.repositories.entities.Doctor;
import com.example.demo.repositories.entities.Policlinic;
import com.example.demo.request.CreatePoliclinicRequest;
import com.example.demo.request.UpdatePoliclinicRequest;
import com.example.demo.response.CreatePoliclinicResponse;
import com.example.demo.response.UpdatePoliclinicResponse;
import com.example.demo.service.PoliclinicService;

@RestController
@RequestMapping("/policlinic")
public class PoliclinicController {

	private final PoliclinicService policlinicService;
	private final DoctorRepository doctorRepository;
	
	public PoliclinicController(PoliclinicService policlinicService, DoctorRepository doctorRepository) {
		this.policlinicService = policlinicService;
		this.doctorRepository = doctorRepository;
	}
	
	@GetMapping("/{id}")
	public Policlinic getById(@PathVariable long id) {
		return policlinicService.getById(id);
	}
	
	@GetMapping
	public List<Policlinic> getAll() {
		return policlinicService.getall();
	}
	
	@GetMapping("/{id}/doctors")
	public List<Doctor> getDoctorsByPoliclinicId(@PathVariable long id) {
        Policlinic policlinic = policlinicService.findById(id);
        if (policlinic != null) {
            return policlinic.getDoctors();
        }
        return null;
	}

	@PostMapping
	public ResponseEntity<CreatePoliclinicResponse> addPoliclinic(@RequestBody CreatePoliclinicRequest policlinicDTO) {
		CreatePoliclinicResponse policlinic = policlinicService.create(policlinicDTO);
		return ResponseEntity.ok(policlinic);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UpdatePoliclinicResponse> updatePoliclinic(@PathVariable long id, @RequestBody UpdatePoliclinicRequest policlinicDTO) {
		UpdatePoliclinicResponse policlinic = policlinicService.update(id, policlinicDTO);
		return ResponseEntity.ok(policlinic);
	}
	
	@DeleteMapping("/{id}")
	public void deletePoliclinic(@PathVariable long id) {
		policlinicService.delete(id);
	}

	@PostMapping("/{policlinicId}/add-doctor/{doctorId}")
	public ResponseEntity<Void> addDoctorToPoliclinic(@PathVariable long policlinicId, @PathVariable long doctorId) {
		policlinicService.addDoctor(policlinicId, doctorId);
		return ResponseEntity.ok().build();
	}
}
