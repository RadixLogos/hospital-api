package com.radixlogos.hospitalapi.controllers;

import com.radixlogos.hospitalapi.dtos.insertions.hospital.HospitalInsertDTO;
import com.radixlogos.hospitalapi.dtos.responses.HospitalResponseDTO;
import com.radixlogos.hospitalapi.services.HospitalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public ResponseEntity<Page<HospitalResponseDTO>> findAllHospitals(Pageable pageable){
        var response = hospitalService.findAllHospitals(pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponseDTO> findHospitalById(@PathVariable Long id){
        var response = hospitalService.findHospitalById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<HospitalResponseDTO> insertHospital(@Valid @RequestBody HospitalInsertDTO insertDTO){
        var response = hospitalService.insertHospital(insertDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalResponseDTO> updateHospital(
            @PathVariable Long id,
            @Valid  @RequestBody HospitalInsertDTO insertDTO){
        var response = hospitalService.updateHospital(id, insertDTO);
        return ResponseEntity.ok().body(response);
    }
}
