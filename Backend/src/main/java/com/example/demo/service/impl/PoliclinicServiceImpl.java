package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.DoctorRepository;
import com.example.demo.repositories.PoliclinicRepository;
import com.example.demo.repositories.entities.Doctor;
import com.example.demo.repositories.entities.Policlinic;
import com.example.demo.request.CreatePoliclinicRequest;
import com.example.demo.request.UpdatePoliclinicRequest;
import com.example.demo.response.CreatePoliclinicResponse;
import com.example.demo.response.UpdatePoliclinicResponse;
import com.example.demo.service.PoliclinicService;

import jakarta.transaction.Transactional;

@Service
public class PoliclinicServiceImpl implements PoliclinicService {

    private final PoliclinicRepository PoliclinicRepository;
    private final DoctorRepository doctorrepo;

    
    public PoliclinicServiceImpl(PoliclinicRepository PoliclinicRepository, DoctorRepository doctorrepo) {
        this.PoliclinicRepository = PoliclinicRepository;
        this.doctorrepo = doctorrepo;
    }

    
	@Override
	public Policlinic getById(long id) {
		Policlinic policlinic = PoliclinicRepository.findByIdAndIsDeletedFalse(id);
		return policlinic;
	}
    
    
    @Override
    public List<Policlinic> getall() {
        return PoliclinicRepository.findAllByIsDeletedFalse();
    }

    @Override
    public Policlinic findById(long id) {
        return PoliclinicRepository.findByIdAndIsDeletedFalse(id); 
    }



	@Override
	public CreatePoliclinicResponse create(CreatePoliclinicRequest policlinicDTO) {
		Policlinic policlinic = Policlinic.fromDTO(policlinicDTO);
		Policlinic savedpoliclinic = PoliclinicRepository.save(policlinic);
		return Policlinic.toDTO(savedpoliclinic);
	}

	@Override
	public void delete(long id) {
	Policlinic policlinic = PoliclinicRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Policlinic not found."));
	policlinic.setDeleted(true);
	PoliclinicRepository.save(policlinic);
	}

	@Override
	public UpdatePoliclinicResponse update(long id,UpdatePoliclinicRequest policlinicDTO) {
		Policlinic policlinic = PoliclinicRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Policlinic not found."));
		policlinic.UpdatefromDTO(policlinicDTO);
		Policlinic savedpoliclinic = PoliclinicRepository.save(policlinic);
		return Policlinic.UpdatetoDTO(savedpoliclinic);
	}


	@Transactional
    @Override
    public void addDoctor(long policlinicId, long doctorId) {
        Policlinic policlinic = PoliclinicRepository.findById(policlinicId).orElseThrow(() -> new IllegalArgumentException("Policlinic not found."));
        Doctor doctor = doctorrepo.findById(doctorId).orElseThrow(() -> new IllegalArgumentException("Doctor not found."));
        
        doctor.setPoliclinic(policlinic);
        doctorrepo.save(doctor);
    }


}
