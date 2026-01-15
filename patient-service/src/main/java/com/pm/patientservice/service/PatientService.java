package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientResponseDTO;

import java.util.List;

public interface PatientService {
    List<PatientResponseDTO> getPatients();
}
