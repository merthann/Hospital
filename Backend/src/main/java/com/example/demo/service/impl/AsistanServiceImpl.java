package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.repositories.AsistanRepository;
import com.example.demo.repositories.entities.Asistan;
import com.example.demo.repositories.entities.Doctor;
import com.example.demo.request.CreateAsistanRequest;
import com.example.demo.request.UpdateAsistanRequest;
import com.example.demo.response.CreateAsistanResponse;
import com.example.demo.response.UpdateAsistanResponse;
import com.example.demo.service.AsistanService;

@Service
public class AsistanServiceImpl implements AsistanService{
	
	private final AsistanRepository asistanrepo;
	
	public AsistanServiceImpl(AsistanRepository asistanrepo) {
		this.asistanrepo = asistanrepo;
	}


	@Override
	public Asistan getById(long id) {
		Asistan asistan = asistanrepo.findByIdAndIsDeletedFalse(id);
		return asistan;
	}
	
	
	@Override
	public List<Doctor> findById(Long id) {
		List<Asistan> asistans = asistanrepo.findByIdAndIsDeletedFalse(id);
		return asistans.stream().flatMap(asistan -> asistan.getDoctors().stream()).collect(Collectors.toList());
	}

	@Override
	public String getWorkdaysById(long id) {
		Asistan asistan = asistanrepo.findByIdAndIsDeletedFalse(id);
		if(asistan != null) {
		return asistan.getWorkdays();
		}
		else {
			return null;
		} 
	}

	@Override
	public CreateAsistanResponse create(CreateAsistanRequest asistanDTO) {
		Asistan asistan = Asistan.fromDTO(asistanDTO);
		Asistan savedasistan = asistanrepo.save(asistan);
		return Asistan.toDTO(savedasistan);
	}

	@Override
	public List<Asistan> getAll() {
		return asistanrepo.findAllByIsDeletedFalse();
	}

	@Override
	public UpdateAsistanResponse update(long id,UpdateAsistanRequest asistanDTO) {
		Asistan asistan = asistanrepo.findById(id).orElseThrow(()->new IllegalArgumentException("Asistan not found."));
		asistan.UpdateFromDTO(asistanDTO);
		Asistan updatedasistan = asistanrepo.save(asistan);
		return Asistan.UpdatetoDTO(updatedasistan);
	}

	@Override
	public void delete(long id) {
		Asistan asistan = asistanrepo.findById(id).orElseThrow(()->new IllegalArgumentException("Asistan not found."));
		asistan.setDeleted(true);
		asistanrepo.save(asistan);
		
	}

}
