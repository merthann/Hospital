package com.example.demo.request;

import com.example.demo.repositories.entities.Category;

public record CreateMedicineRequest(String name,String description, Category category) {

}