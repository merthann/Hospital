package com.example.demo.repositories.entities;

import java.time.LocalDate;

import com.example.demo.repositories.entities.base.BaseEntity;
import com.example.demo.request.CreateAppointmentRequest;
import com.example.demo.request.UpdateAppointmentRequest;
import com.example.demo.response.CreateAppointmentResponse;
import com.example.demo.response.UpdateAppointmentResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "Appointment")
@Entity
public class Appointment  extends BaseEntity{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "patientid", nullable = false)
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "doctorid", nullable = false )
	private Doctor doctor;
	
	@Column(name = "time")
	@Enumerated(EnumType.ORDINAL)
	private TimeSlot appointmenttime;
	
	@Column(name = "date")
	private LocalDate appointmentDate;
	
	
	public static CreateAppointmentResponse toDTO(Appointment appointment) {
		return new CreateAppointmentResponse(appointment.id,appointment.patient.getId(),appointment.doctor.getId(),appointment.getAppointmenttime(), appointment.getAppointmentDate());
	}
	
	public static Appointment fromDTO(CreateAppointmentRequest appointmentdto,Doctor doctor,Patient patient) {
		return Appointment.builder().patient(patient).doctor(doctor).appointmenttime(appointmentdto.time()).appointmentDate(appointmentdto.date()).build();
	}
	
	public void UpdatefromDTO(UpdateAppointmentRequest appointmentdto,Doctor doctor) {
		this.doctor = doctor;
		this.appointmenttime = appointmentdto.time();
		this.appointmentDate = appointmentdto.date();
	}
	
	public static UpdateAppointmentResponse updateToDTO(Appointment appointment) {
        return new UpdateAppointmentResponse(appointment.id, appointment.patient.getId(), appointment.doctor.getId(), appointment.appointmenttime, appointment.appointmentDate);
    }
	

}
