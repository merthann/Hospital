package com.example.demo.service.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import com.example.demo.repositories.PatientRepository;
import com.example.demo.repositories.entities.Patient;
import com.example.demo.request.CreatePatientRequest;
import com.example.demo.request.UpdatePatientRequest;
import com.example.demo.response.CreatePatientResponse;
import com.example.demo.response.UpdatePatientResponse;
import com.example.demo.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{
	
	private final PatientRepository repo;
	private final ModelMapper modelMapper;
	public PatientServiceImpl(PatientRepository repo,ModelMapper modelMapper) {
		this.repo = repo;
		this.modelMapper = modelMapper;
	}
	
	
	@Override
	public Patient getById(UUID id) {
		Patient patient = repo.findByIdAndIsDeletedFalse(id);
		return patient;
	}
	
	
	@Override
	public List<Patient> getall() {
		return this.repo.findAllByIsDeletedFalse();
	}
	
	@Override
	public CreatePatientResponse create(CreatePatientRequest patientdto) {
		Patient f = Patient.fromDTO(patientdto);
		Patient savedpatient = repo.save(f);
		return Patient.toDTO(savedpatient);
	}
	@Override
	public UpdatePatientResponse update(UUID id, UpdatePatientRequest patientDTO) {
		Patient patient = repo.findById(id).orElseThrow(()->new IllegalArgumentException("Patient not found."));
		patient.UpdateFromDTO(patientDTO);
		Patient savedpatient = repo.save(patient);
		return Patient.UpdatetoDTO(savedpatient);
	}


	@Override
	public void delete(UUID id) {
	Patient patient = repo.findById(id).orElseThrow(()->new IllegalArgumentException("Patient not found."));
	patient.setDeleted(true);
	repo.save(patient);
	}

}
