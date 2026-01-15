package com.pm.patientservice.service.impl;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.repository.PatientRepository;
import com.pm.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Override
    public List<PatientResponseDTO> getPatients() {
        return null;
    }
}
