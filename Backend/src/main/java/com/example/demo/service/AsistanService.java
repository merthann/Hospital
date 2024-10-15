package com.example.demo.service;

import java.util.List;

import com.example.demo.repositories.entities.Asistan;
import com.example.demo.repositories.entities.Doctor;
import com.example.demo.request.CreateAsistanRequest;
import com.example.demo.request.UpdateAsistanRequest;
import com.example.demo.response.CreateAsistanResponse;
import com.example.demo.response.UpdateAsistanResponse;

public interface AsistanService {
	List<Asistan> getAll();
	Asistan getById(long id);
	List<Doctor> findById(Long id);
	String getWorkdaysById(long id);
	CreateAsistanResponse create(CreateAsistanRequest asistanDTO);
	UpdateAsistanResponse update(long id,UpdateAsistanRequest asistanDTO);
	void delete(long id);
}
 