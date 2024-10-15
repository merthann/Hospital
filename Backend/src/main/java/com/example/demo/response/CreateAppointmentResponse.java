package com.example.demo.response;

import java.time.LocalDate;
import java.util.UUID;

import com.example.demo.repositories.entities.TimeSlot;

public record CreateAppointmentResponse(long id,UUID patientid,long doctorid,TimeSlot time,LocalDate date) {

}
