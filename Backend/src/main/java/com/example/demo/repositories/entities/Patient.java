package com.example.demo.repositories.entities;

import java.util.Set;
import java.util.UUID;

import com.example.demo.repositories.entities.base.BaseEntity;
import com.example.demo.request.CreatePatientRequest;
import com.example.demo.request.UpdatePatientRequest;
import com.example.demo.response.CreatePatientResponse;
import com.example.demo.response.UpdatePatientResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;

//import org.hibernate.annotations.UuidGenerator;
//import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "Patient")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient extends BaseEntity{
	
	
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
	private UUID id;
    
    @Column(name = "fname")
	private String fname;
    
    @Column(name = "lname")
	private String lname;
    
    @Column(name = "identityno",unique = true)
    @Min(value = 10000000000L, message = "Identity number must be at least 11 digits")
    @Max(value = 99999999999L, message = "Identity number must be at most 11 digits")
	private long identityno;
    
    @Email
    @Column(name = "email",unique = true)
    private String email;
    
    @Column(name = "mobileno")
    @Min(value = 1000000000L, message = "Mobile number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Mobile number must be at most 10 digits")
    private long mobileno;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.LAZY ,mappedBy = "patient")
    private Set<Appointment> appointments;
    
    public static CreatePatientResponse toDTO(Patient patient) {
    	return new CreatePatientResponse(patient.getId(),patient.getFname(), patient.getLname());
    }
    
    public static Patient fromDTO(CreatePatientRequest patientDTO) {
    	return Patient.builder().fname(patientDTO.fname()).lname(patientDTO.lname())
    			.identityno(patientDTO.identityno()).email(patientDTO.email())
    				.mobileno(patientDTO.mobileno()).build();
    }
    
    
    public void UpdateFromDTO(UpdatePatientRequest patientDTO) {
    	this.fname = patientDTO.fname();
    	this.lname = patientDTO.lname();
    	this.identityno = patientDTO.identityno();
    	this.email = patientDTO.email();
    	this.mobileno = patientDTO.mobileno();
    }
    
    public static UpdatePatientResponse UpdatetoDTO(Patient patient) {
    	return new UpdatePatientResponse(patient.getId(),patient.getFname(), patient.getLname());
    }
    

}


