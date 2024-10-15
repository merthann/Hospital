package com.example.demo.response;

import java.util.UUID;

public record CreatePatientResponse(UUID id,String fname,String lname) {
}