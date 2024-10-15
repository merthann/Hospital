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

import com.example.demo.repositories.entities.Asistan;
import com.example.demo.repositories.entities.Doctor;
import com.example.demo.request.CreateAsistanRequest;
import com.example.demo.request.UpdateAsistanRequest;
import com.example.demo.response.CreateAsistanResponse;
import com.example.demo.response.UpdateAsistanResponse;
import com.example.demo.service.AsistanService;

@RestController
@RequestMapping("/asistan")
public class AsistanController {
	private final AsistanService service;
	
	public AsistanController(AsistanService asistanservice) {
		this.service = asistanservice;
	}
	
	
	@GetMapping("/{id}")
	public Asistan getbyId(@PathVariable long id) {
		return service.getById(id);
	}
	
	
	@GetMapping
	public List<Asistan> getall(){
		return service.getAll();
	}
	
	
	@GetMapping("/{id}/hizmet-ettigi-doktorlar")
	public List<Doctor> findById(@PathVariable long id){
		return service.findById(id);
		
	}
	
	@GetMapping("/{id}/calismagunleri")
	public String getworkdaysById(@PathVariable long id) {
		return service.getWorkdaysById(id);
	}
	
	@PostMapping
	public ResponseEntity<CreateAsistanResponse> createAsistan(@RequestBody CreateAsistanRequest asistanDTO) {
		CreateAsistanResponse asistan = service.create(asistanDTO);
		return ResponseEntity.ok(asistan);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UpdateAsistanResponse> UpdateAsistan(@PathVariable long id,@RequestBody UpdateAsistanRequest asistanDTO){
		UpdateAsistanResponse updatedasistan = service.update(id, asistanDTO);
		return ResponseEntity.ok(updatedasistan);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAsistan(@PathVariable long id) {
		service.delete(id);
	}
}
