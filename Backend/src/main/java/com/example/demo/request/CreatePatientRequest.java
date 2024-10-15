package com.example.demo.request;

public record CreatePatientRequest (String fname, String lname,long identityno,String email,long mobileno) {}
