package com.example.demo.response;

import com.example.demo.repositories.entities.Category;

public record UpdateMedicineResponse(long id,String name,String description,Category category) {
}