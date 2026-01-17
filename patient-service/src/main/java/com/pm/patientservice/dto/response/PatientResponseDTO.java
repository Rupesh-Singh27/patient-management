package com.pm.patientservice.dto.response;

public record PatientResponseDTO(
        String id,
        String name,
        String email,
        String dateOfBirth,
        String address
) { }
