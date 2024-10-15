package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.repositories.AsistanRepository;
import com.example.demo.repositories.DoctorRepository;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.repositories.PoliclinicRepository;
import com.example.demo.repositories.entities.Appointment;
import com.example.demo.repositories.entities.Asistan;
import com.example.demo.repositories.entities.Doctor;
import com.example.demo.repositories.entities.Patient;
import com.example.demo.repositories.entities.Policlinic;
import com.example.demo.repositories.AppointmentRepository;

/*
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AsistanRepository asistanRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PoliclinicRepository policlinicRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create Asistan
        Asistan asistan = new Asistan();
        asistan.setFname("mahmut");
        asistan.setLname("kadir");
        asistan.setWorkdays("pzt-çrş");
        asistan = asistanRepository.save(asistan);

        // Create Policlinic
        Policlinic policlinic = new Policlinic();
        policlinic.setName("Kulak burun boğaz");
        policlinic.setFloor(1);
        policlinic = policlinicRepository.save(policlinic);

        // Create Doctor
        Doctor doctor = new Doctor();
        doctor.setFname("Sadi");
        doctor.setLname("Kaput");
        doctor.setTitle("Prof. Dr.");
        doctor.setAsistan(asistan);
        doctor.setPoliclinic(policlinic);
        doctor = doctorRepository.save(doctor);

        // Create Patient
        Patient patient = new Patient();
        patient.setFname("Ahmet");
        patient.setLname("Keriz");
        patient.setIdentityno(234543623);
        patient = patientRepository.save(patient);

        // Create Appointment
        Appointment appointment = new Appointment();
        appointment.setAppointmentDateTime(LocalDateTime.of(2024, 8, 27, 12, 30, 0));
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment = appointmentRepository.save(appointment);

        System.out.println("Data initialized successfully");
    }
}
*/