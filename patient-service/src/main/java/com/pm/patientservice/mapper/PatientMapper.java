package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.request.PatientRequestDTO;
import com.pm.patientservice.dto.response.PatientResponseDTO;
import com.pm.patientservice.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    PatientResponseDTO mapPatientToPatientResponseDTO(Patient patient);

    Patient mapPatientResponseDTOToPatient(PatientResponseDTO patientResponseDTO);

    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "registeredDate", target = "registeredDate")
    Patient mapPatientRequestDTOToPatient(PatientRequestDTO patientRequestDTO);
}
