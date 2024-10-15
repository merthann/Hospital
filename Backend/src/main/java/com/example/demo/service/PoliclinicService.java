package com.example.demo.service;

import java.util.List;

import com.example.demo.repositories.entities.Policlinic;
import com.example.demo.request.CreatePoliclinicRequest;
import com.example.demo.request.UpdatePoliclinicRequest;
import com.example.demo.response.CreatePoliclinicResponse;
import com.example.demo.response.UpdatePoliclinicResponse;

public interface PoliclinicService {
    List<Policlinic> getall();
    Policlinic getById(long id);
    Policlinic findById(long id);
    CreatePoliclinicResponse create(CreatePoliclinicRequest policlinicDTO);
    UpdatePoliclinicResponse update(long id, UpdatePoliclinicRequest policlinicDTO);
    void delete(long id);
    void addDoctor(long policlinicId, long doctorId);
}
