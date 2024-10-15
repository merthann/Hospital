package com.example.demo.repositories.entities;

import java.util.List;

import com.example.demo.repositories.entities.base.BaseEntity;
import com.example.demo.request.CreateAsistanRequest;
import com.example.demo.request.UpdateAsistanRequest;
import com.example.demo.response.CreateAsistanResponse;
import com.example.demo.response.UpdateAsistanResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Asistan")
@Data
public class Asistan extends BaseEntity{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "fname")
	private String fname;

	@Column(name = "lname")
	private String lname;
	
	@Column(name = "workdays")
	private String workdays;
	
	@OneToMany(mappedBy = "asistan", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Doctor> doctors;
	
	public static CreateAsistanResponse toDTO(Asistan asistan) {
		return new CreateAsistanResponse(asistan.id,asistan.getFname(), asistan.getLname());
	}
	
	public static Asistan fromDTO(CreateAsistanRequest asistanDTO) {
		return Asistan.builder().fname(asistanDTO.fname()).lname(asistanDTO.lname()).workdays(asistanDTO.workdays()).build();
	}
	
	public void UpdateFromDTO(UpdateAsistanRequest asistandto) {
		this.fname = asistandto.fname();
		this.lname = asistandto.lname();
		this.workdays = asistandto.workdays();
	}
	
	public static UpdateAsistanResponse UpdatetoDTO(Asistan asistan) {
		return new UpdateAsistanResponse(asistan.id,asistan.getFname(), asistan.getLname());
	}
	
}
