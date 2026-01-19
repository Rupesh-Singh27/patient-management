package com.pm.patientservice.controller;

import com.pm.patientservice.dto.request.PatientRequestDTO;
import com.pm.patientservice.dto.response.PatientResponseDTO;
import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    @Operation(summary = "Get all the patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatient(){
        return new ResponseEntity<>(patientService.getPatients(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a patient")
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO
    ){
        return new ResponseEntity<>(patientService.createPatient(patientRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{patientId}")
    @Operation(summary = "Update the patient based on it's ID")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable UUID patientId,
            @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO
    ){
        return new ResponseEntity<>(patientService.updatePatient(patientId, patientRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{patientId}")
    @Operation(summary = "Delete the patient based on ID")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID patientId){
        patientService.deletePatientById(patientId);
        return ResponseEntity.noContent().build();
    }
}
