package com.radixlogos.hospitalapi.controllers;


import com.radixlogos.hospitalapi.dtos.insertions.PatientInsertDTO;
import com.radixlogos.hospitalapi.dtos.responses.PatientResponseDTO;
import com.radixlogos.hospitalapi.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/hospitals/{hospitalId}/users/{userId}/patients")
public class UserPatientController {
    @Autowired
    public PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> insertPatient(
            @PathVariable Long hospitalId,
            @PathVariable Long userId,
            @Valid @RequestBody PatientInsertDTO registrationDTO)
    {
        PatientResponseDTO patientResponse = patientService.insertPatient(hospitalId,userId,registrationDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/userId").buildAndExpand(patientResponse.id()).toUri();
        return ResponseEntity.created(uri).body(patientResponse);
    }

}
