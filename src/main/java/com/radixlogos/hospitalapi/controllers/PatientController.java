package com.radixlogos.hospitalapi.controllers;

import com.radixlogos.hospitalapi.dtos.insertions.PatientInsertDTO;
import com.radixlogos.hospitalapi.dtos.responses.PatientFullResponseDTO;
import com.radixlogos.hospitalapi.dtos.responses.PatientResponseDTO;
import com.radixlogos.hospitalapi.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping
    public ResponseEntity<Page<PatientFullResponseDTO>> findAll(Pageable pageable){
        var response = patientService.findAllPatients(pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> findPatient(@PathVariable Long id){
        var response = patientService.findPatientById(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable Long id,
            @RequestBody PatientInsertDTO insertDTO){
        var response = patientService.updatePatient(id,insertDTO);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
