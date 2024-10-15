package com.example.demo.response;

import java.util.UUID;

public record UpdatePatientResponse(UUID id,String fname,String lname) {
}
