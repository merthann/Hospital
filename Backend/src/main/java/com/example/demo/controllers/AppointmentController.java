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

import com.example.demo.repositories.entities.Appointment;
import com.example.demo.request.CreateAppointmentRequest;
import com.example.demo.request.UpdateAppointmentRequest;
import com.example.demo.response.CreateAppointmentResponse;
import com.example.demo.response.UpdateAppointmentResponse;
import com.example.demo.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	private final AppointmentService service;
	
	public AppointmentController(AppointmentService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	public Appointment getbyId(@PathVariable long id) {
		return service.getById(id);
	}
	
	
	@GetMapping
	public List<Appointment> getAll(){
		return service.getAll();
	}
	
	@PostMapping
	public ResponseEntity<CreateAppointmentResponse> createAppointment(@RequestBody CreateAppointmentRequest appointmentdto){
		CreateAppointmentResponse createdappointmentDTO = service.create(appointmentdto);
		return ResponseEntity.ok(createdappointmentDTO);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<UpdateAppointmentResponse> updateAppointment(@PathVariable long id, @RequestBody UpdateAppointmentRequest appointmentDTO) {
        UpdateAppointmentResponse updatedAppointment = service.update(id, appointmentDTO);
        return ResponseEntity.ok(updatedAppointment);
    }
	
	@DeleteMapping("/{id}")
	public void deleteAppointment(@PathVariable long id) {
		service.delete(id);
	}
}
 