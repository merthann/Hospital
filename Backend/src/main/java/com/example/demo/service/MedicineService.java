package com.example.demo.service;

import java.util.List;

import com.example.demo.repositories.entities.Category;
import com.example.demo.repositories.entities.Medicine;
import com.example.demo.request.CreateMedicineRequest;
import com.example.demo.request.UpdateMedicineRequest;
import com.example.demo.response.CreateMedicineResponse;
import com.example.demo.response.UpdateMedicineResponse;

public interface MedicineService {
	List<Medicine> getall();
	Medicine getById(long id);
	List<Medicine> getMedicineByCategory(Category category);
	CreateMedicineResponse create(CreateMedicineRequest medicineDTO);
	UpdateMedicineResponse update(long id,UpdateMedicineRequest medicineDTO);
	public void delete(long id);
}
