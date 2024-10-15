package com.example.demo.request;

public record UpdateDoctorRequest(String title, String fname, String lname, long policlinicId, long asistanId) {}
