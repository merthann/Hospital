package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.demo.repositories.AppointmentRepository;
import com.example.demo.repositories.DoctorRepository;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.repositories.entities.Appointment;
import com.example.demo.repositories.entities.Doctor;
import com.example.demo.repositories.entities.Patient;
import com.example.demo.request.CreateAppointmentRequest;
import com.example.demo.request.UpdateAppointmentRequest;
import com.example.demo.response.CreateAppointmentResponse;
import com.example.demo.response.UpdateAppointmentResponse;
import com.example.demo.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	private final AppointmentRepository appointmentrepo;
	private final PatientRepository patientrepo;
	private final DoctorRepository doctorrepo;
	
	public AppointmentServiceImpl(AppointmentRepository appointmentrepo,PatientRepository patientrepo,DoctorRepository doctorrepo) {
		this.appointmentrepo = appointmentrepo;
		this.patientrepo = patientrepo;
		this.doctorrepo = doctorrepo;
	}
	
	@Override
	public Appointment getById(long id) {
		Appointment appointment = appointmentrepo.findByIdAndIsDeletedFalse(id);
		return appointment;
	}

	@Override
	public List<Appointment> getAll() {
		return appointmentrepo.findAllByIsDeletedFalse();
	}

	@Override
	public CreateAppointmentResponse create(CreateAppointmentRequest appointmentdto) {
        Patient patient = patientrepo.findById(appointmentdto.patientid())
	            .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));
	        Doctor doctor = doctorrepo.findById(appointmentdto.doctorid())
	            .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID"));

		Appointment appointment = Appointment.fromDTO(appointmentdto,doctor,patient);
		Appointment savedappointment = appointmentrepo.save(appointment);
		return Appointment.toDTO(savedappointment);
	}
	

	@Override
	public UpdateAppointmentResponse update(long id, UpdateAppointmentRequest appointmentDTO) {
	    Appointment appointment = appointmentrepo.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Appointment not found."));
	    Doctor doctor = doctorrepo.findById(appointmentDTO.doctorid())
	            .orElseThrow(() -> new IllegalArgumentException("Doctor not found."));
	    
	    appointment.UpdatefromDTO(appointmentDTO, doctor);
	    Appointment updatedappointment = appointmentrepo.save(appointment);
	    return Appointment.updateToDTO(updatedappointment);
	}


	@Override
	public void delete(long id) {
		Appointment appointment = appointmentrepo.findById(id).orElseThrow(()->new IllegalArgumentException("Appointment not found."));
		appointment.setDeleted(true);
		appointmentrepo.save(appointment);
	}


}
