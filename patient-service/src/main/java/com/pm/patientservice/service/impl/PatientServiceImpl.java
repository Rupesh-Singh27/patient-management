package com.pm.patientservice.service.impl;

import com.pm.patientservice.dto.request.PatientRequestDTO;
import com.pm.patientservice.dto.response.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import com.pm.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
        if(patientRepository.existsByEmail(patientRequestDTO.email())){
            throw new EmailAlreadyExistsException("Patients already exists with email: " + patientRequestDTO.email());
        }

        Patient patient = patientMapper.mapPatientRequestDTOToPatient(patientRequestDTO);
        Patient savePatient = patientRepository.save(patient);
        return patientMapper.mapPatientToPatientResponseDTO(savePatient);
    }

    @Override
    public PatientResponseDTO updatePatient(UUID patientId, PatientRequestDTO patientRequestDTO){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not available with id:" + patientId));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.email(), patientId)){
            throw new EmailAlreadyExistsException("Patients already exists with email: " + patientRequestDTO.email());
        }

        patient.setName(patientRequestDTO.name());
        patient.setEmail(patientRequestDTO.email());
        patient.setAddress(patientRequestDTO.address());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.dateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);

        return patientMapper.mapPatientToPatientResponseDTO(updatedPatient);
    }

    @Override
    public void deletePatientById(UUID patientId) {
        patientRepository.deleteById(patientId);
    }
}
