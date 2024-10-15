package com.example.demo.service;

import java.util.List;

import com.example.demo.repositories.entities.Doctor;
import com.example.demo.request.CreateDoctorRequest;
import com.example.demo.request.UpdateDoctorRequest;
import com.example.demo.response.CreateDoctorResponse;
import com.example.demo.response.UpdateDoctorResponse;

public interface DoctorService {
	List<Doctor> getAll();
	Doctor getById(long id);
	List<Doctor> getByPoliclinicId(long policlinicid);
	CreateDoctorResponse create(CreateDoctorRequest doctorDTO);
	UpdateDoctorResponse update(Long id, UpdateDoctorRequest doctorDTO);
	void delete(long id);

}
 