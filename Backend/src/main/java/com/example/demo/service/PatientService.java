package com.example.demo.service;

import java.util.List;
import java.util.UUID;


import com.example.demo.repositories.entities.Patient;
import com.example.demo.request.CreatePatientRequest;
import com.example.demo.request.UpdatePatientRequest;

import com.example.demo.response.CreatePatientResponse;
import com.example.demo.response.UpdatePatientResponse;

public interface PatientService {
	List<Patient> getall();
	Patient getById(UUID id);
    CreatePatientResponse create(CreatePatientRequest patientDTO);
    UpdatePatientResponse update(UUID id,UpdatePatientRequest patientDTO);
    void delete(UUID id);
}
