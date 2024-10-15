package com.example.demo.service;

import com.example.demo.repositories.entities.Appointment;
import com.example.demo.request.CreateAppointmentRequest;
import com.example.demo.request.UpdateAppointmentRequest;
import com.example.demo.response.CreateAppointmentResponse;
import com.example.demo.response.UpdateAppointmentResponse;
import java.util.List;


public interface AppointmentService {
    List<Appointment> getAll();
    Appointment getById(long id);
    CreateAppointmentResponse create(CreateAppointmentRequest appointmentdto);
    UpdateAppointmentResponse update(long id,UpdateAppointmentRequest apppointmentdto);
    void delete(long id);
}