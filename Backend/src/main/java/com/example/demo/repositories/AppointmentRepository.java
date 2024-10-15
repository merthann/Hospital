package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repositories.entities.Appointment;
import com.example.demo.repositories.entities.TimeSlot;


public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	List<Appointment> findAllByIsDeletedFalse();
	Appointment findByIdAndIsDeletedFalse(long id);
	Optional<Appointment> findByAppointmenttimeAndIsDeletedFalse(TimeSlot appointmenttime);
	
}