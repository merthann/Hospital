package com.example.demo.request;

import java.time.LocalDate;
import java.util.UUID;

import com.example.demo.repositories.entities.TimeSlot;

public record UpdateAppointmentRequest(long doctorid,TimeSlot time,LocalDate date) {

}
