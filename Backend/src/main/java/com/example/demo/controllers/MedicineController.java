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

import com.example.demo.repositories.entities.Category;
import com.example.demo.repositories.entities.Medicine;
import com.example.demo.request.CreateMedicineRequest;
import com.example.demo.request.UpdateMedicineRequest;
import com.example.demo.response.CreateMedicineResponse;
import com.example.demo.response.UpdateMedicineResponse;
import com.example.demo.service.MedicineService;
@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService service;

    public MedicineController(MedicineService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Medicine getById(@PathVariable long id) {
        return service.getById(id);
    }
    @GetMapping
	public List<Medicine> getall(){
		return service.getall();
	}

    @GetMapping("/category/{category}")
    public List<Medicine> findByCategory(@PathVariable Category category) {
        return service.getMedicineByCategory(category);
    }

    @PostMapping
    public ResponseEntity<CreateMedicineResponse> createMedicine(@RequestBody CreateMedicineRequest medicineDTO) {
        CreateMedicineResponse medicine = service.create(medicineDTO);
        return ResponseEntity.ok(medicine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateMedicineResponse> updateMedicine(@PathVariable long id, @RequestBody UpdateMedicineRequest medicineDTO) {
        UpdateMedicineResponse medicine = service.update(id, medicineDTO);
        return ResponseEntity.ok(medicine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
