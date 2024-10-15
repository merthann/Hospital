package com.example.demo.request;

public record UpdatePatientRequest(String fname,String lname,long identityno,String email,long mobileno) {
}
