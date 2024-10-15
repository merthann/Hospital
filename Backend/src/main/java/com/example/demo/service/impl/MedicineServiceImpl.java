package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.repositories.MedicineRepository;
import com.example.demo.repositories.entities.Category;
import com.example.demo.repositories.entities.Medicine;
import com.example.demo.request.CreateMedicineRequest;
import com.example.demo.request.UpdateMedicineRequest;
import com.example.demo.response.CreateMedicineResponse;
import com.example.demo.response.UpdateMedicineResponse;
import com.example.demo.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService{

	private final MedicineRepository repo;
	public MedicineServiceImpl(MedicineRepository repo) {
		this.repo = repo;
	}
	
	
	 @Override
	    public Medicine getById(long id) {
	        return repo.findByIdAndIsDeletedFalse(id);
	    }
	
	@Override
	public List<Medicine> getall() {
		return this.repo.findAllByIsDeletedFalse();
	}

	@Override
	public List<Medicine> getMedicineByCategory(Category category) {
		return repo.findByCategoryAndIsDeletedFalse(category);
	}

	@Override
	public CreateMedicineResponse create(CreateMedicineRequest medicineDTO) {
		Medicine medicine = Medicine.fromDTO(medicineDTO);
		Medicine savedmedicine = repo.save(medicine);
		return Medicine.toDTO(savedmedicine);
	}

	@Override
	public UpdateMedicineResponse update(long id, UpdateMedicineRequest medicineDTO) {
		Medicine medicine = repo.findById(id).orElseThrow(()->new IllegalArgumentException("Medicine not found."));
		medicine.UpdateFromDTO(medicineDTO);
		Medicine updatedmedicine = repo.save(medicine);
		return Medicine.UpdatetoDTO(updatedmedicine);
	}

	@Override
	public void delete(long id) {
		Medicine medicine = repo.findById(id).orElseThrow(()->new IllegalArgumentException("Medicine not found."));
		medicine.setDeleted(true);
		repo.save(medicine);
	}


}
