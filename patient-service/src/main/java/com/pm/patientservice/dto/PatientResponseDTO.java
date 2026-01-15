package com.pm.patientservice.dto;

public record PatientResponseDTO(
        String id,
        String name,
        String email,
        String dateOfBirth,
        String address
) { }
