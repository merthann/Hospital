package com.example.demo.repositories.entities;


import java.util.Set;

import com.example.demo.repositories.entities.base.BaseEntity;
import com.example.demo.request.CreateDoctorRequest;
import com.example.demo.request.UpdateDoctorRequest;
import com.example.demo.response.CreateDoctorResponse;
import com.example.demo.response.UpdateDoctorResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Doctor")
@Data
@Builder
public class Doctor extends BaseEntity{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "fname")
	private String fname;
	
	@Column(name = "lname")
	private String lname;
	
	
	@ManyToOne
	@JoinColumn(name = "policlinicid",nullable = false)
	private Policlinic policlinic;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "asistanid",nullable = false)
	private Asistan asistan;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "doctor", fetch = FetchType.LAZY)
	private Set<Appointment> appointments; 
	
	public static CreateDoctorResponse toDTO(Doctor doctor) {
		return new CreateDoctorResponse(doctor.getId(),doctor.getTitle(), doctor.getFname(), doctor.getLname());
	}
	
	public static Doctor fromDTO(CreateDoctorRequest doctorDTO,Policlinic policlinic, Asistan asistan) {
		return Doctor.builder().title(doctorDTO.title()).fname(doctorDTO.fname()).lname(doctorDTO.lname()).policlinic(policlinic).asistan(asistan).build();
	}
	
	public void updateFromDTO(UpdateDoctorRequest doctordto, Policlinic policlinic, Asistan asistan) {
	    this.title = doctordto.title();
	    this.fname = doctordto.fname();
	    this.lname = doctordto.lname();
	    this.policlinic = policlinic;
	    this.asistan = asistan;
	}
	
	public static UpdateDoctorResponse UpdatetoDTO(Doctor doctor) {
		return new UpdateDoctorResponse(doctor.getId(),doctor.getTitle(), doctor.getFname(), doctor.getLname());
	}
}
