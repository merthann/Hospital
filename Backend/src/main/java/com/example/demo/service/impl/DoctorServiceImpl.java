package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.repositories.AsistanRepository;
import com.example.demo.repositories.DoctorRepository;
import com.example.demo.repositories.PoliclinicRepository;
import com.example.demo.repositories.entities.Asistan;
import com.example.demo.repositories.entities.Doctor;
import com.example.demo.repositories.entities.Policlinic;
import com.example.demo.request.CreateDoctorRequest;
import com.example.demo.request.UpdateDoctorRequest;
import com.example.demo.response.CreateDoctorResponse;
import com.example.demo.response.UpdateDoctorResponse;
import com.example.demo.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService{

private final DoctorRepository Doctorrepository;
private final PoliclinicRepository polirepository;
private final AsistanRepository asistanrepo;

public DoctorServiceImpl(DoctorRepository Doctorrepository,PoliclinicRepository polirepository,AsistanRepository asistanrepo) {
	this.Doctorrepository= Doctorrepository;
	this.polirepository = polirepository;
	this.asistanrepo = asistanrepo;
}

	@Override
	public List<Doctor> getAll() {
		return this.Doctorrepository.findAllByIsDeletedFalse();
	}
	
	@Override
	public Doctor getById(long id) {
		Doctor doctor = Doctorrepository.findByIdAndIsDeletedFalse(id);
		return doctor;
	}
 
	
	
	@Override
	public List<Doctor> getByPoliclinicId(long policlinicid) {
		return this.Doctorrepository.findByPoliclinicIdAndIsDeletedFalse(policlinicid);
	}
	
	
	@Override
	public CreateDoctorResponse create(CreateDoctorRequest doctorDTO) {
		Policlinic policlinic = polirepository.findById(doctorDTO.policlinicId())
	            .orElseThrow(() -> new IllegalArgumentException("Invalid policlinic ID"));
	        Asistan asistan = asistanrepo.findById(doctorDTO.asistanId())
	            .orElseThrow(() -> new IllegalArgumentException("Invalid asistan ID"));

		Doctor doctor = Doctor.fromDTO(doctorDTO,policlinic,asistan);
		Doctor saveddoctor = Doctorrepository.save(doctor);
		return Doctor.toDTO(saveddoctor);
	}

	@Override
    public UpdateDoctorResponse update(Long id, UpdateDoctorRequest doctorDTO) {
        Doctor existingDoctor = Doctorrepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        Policlinic policlinic = polirepository.findById(doctorDTO.policlinicId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid policlinic ID"));
        Asistan asistan = asistanrepo.findById(doctorDTO.asistanId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid asistan ID"));

        existingDoctor.updateFromDTO(doctorDTO, policlinic, asistan);
        Doctor updatedDoctor = Doctorrepository.save(existingDoctor);
        return Doctor.UpdatetoDTO(updatedDoctor);
    }

	@Override
	public void delete(long id) {
		Doctor doctor = Doctorrepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Doctor not found."));
		doctor.setDeleted(true);
		Doctorrepository.save(doctor);
	}



}
