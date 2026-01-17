package com.pm.patientservice.service.impl;

import com.pm.patientservice.dto.request.PatientRequestDTO;
import com.pm.patientservice.dto.response.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import com.pm.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private PatientMapper patientMapper = Mappers.getMapper(PatientMapper.class);

    @Override
    public List<PatientResponseDTO> getPatients() {

        List<Patient> allPatients = patientRepository.findAll();
        return allPatients.stream()
                        .map(patient -> patientMapper.mapPatientToPatientResponseDTO(patient))
                        .toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        Patient patient = patientMapper.mapPatientRequestDTOToPatient(patientRequestDTO);
        Patient savePatient = patientRepository.save(patient);
        return patientMapper.mapPatientToPatientResponseDTO(savePatient);
    }
}
