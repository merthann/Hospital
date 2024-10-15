package com.example.demo.repositories.entities;


import java.util.List;

import com.example.demo.repositories.entities.base.BaseEntity;
import com.example.demo.request.CreatePoliclinicRequest;
import com.example.demo.request.UpdatePoliclinicRequest;
import com.example.demo.response.CreatePoliclinicResponse;
import com.example.demo.response.UpdatePoliclinicResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Policlinic")  
@Data
public class Policlinic extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policlinicid")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "floor")
    private int floor;
    
    
    @OneToMany(mappedBy = "policlinic", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Doctor> doctors;

    
    public List<Doctor> getDoctors(){
    	return doctors;
    }
    	
    	public static CreatePoliclinicResponse toDTO(Policlinic policlinic) {
    		return new CreatePoliclinicResponse(policlinic.getId(),policlinic.getName(),policlinic.getFloor());
    	}
    
    	public static Policlinic fromDTO(CreatePoliclinicRequest policlinicDTO) {
    		return Policlinic.builder().name(policlinicDTO.name()).floor(policlinicDTO.floor()).build();
    	}
    	
    	public static UpdatePoliclinicResponse UpdatetoDTO(Policlinic policlinic) {
    		return new UpdatePoliclinicResponse(policlinic.getId(),policlinic.getName(),policlinic.getFloor());
    	}
    	
    	public void UpdatefromDTO(UpdatePoliclinicRequest policlinicDTO) {
    		this.name = policlinicDTO.name();
    		this.floor = policlinicDTO.floor();
    	}
}	
