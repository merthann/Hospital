package com.example.demo.repositories.entities;

import com.example.demo.repositories.entities.base.BaseEntity;
import com.example.demo.request.CreateMedicineRequest;
import com.example.demo.request.UpdateMedicineRequest;
import com.example.demo.response.CreateMedicineResponse;
import com.example.demo.response.UpdateMedicineResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Medicine")
@Entity
@Data

public class Medicine extends BaseEntity{

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "isim")
	private String name;
	 
	@Column(name = "aciklama")
	private String description;
	
	@Column(name = "kategori")
	@Enumerated(EnumType.STRING)
	
	private Category category;
	
	
	public static CreateMedicineResponse toDTO(Medicine medicine) {
		return new CreateMedicineResponse(medicine.getId(),medicine.getName());
	}
	
	public static Medicine fromDTO(CreateMedicineRequest medicineDTO) {
		return Medicine.builder().name(medicineDTO.name()).description(medicineDTO.description()).category(medicineDTO.category()).build();
	}
	
	public void UpdateFromDTO(UpdateMedicineRequest medicineDTO) {
		this.name = medicineDTO.name();
		this.description = medicineDTO.description();
		this.category = medicineDTO.category();
	}
	
	
	public static UpdateMedicineResponse UpdatetoDTO(Medicine medicine) {
		return new UpdateMedicineResponse(medicine.getId(),medicine.getName(),medicine.getDescription(),medicine.getCategory());
	}
	
	
}
