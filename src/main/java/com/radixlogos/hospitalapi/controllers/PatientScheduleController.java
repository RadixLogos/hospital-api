package com.radixlogos.hospitalapi.controllers;

import com.radixlogos.hospitalapi.dtos.responses.ScheduleResponseDTO;
import com.radixlogos.hospitalapi.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients/{id}/schedules")
public class PatientScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<ScheduleResponseDTO> findScheduleByPatient(@PathVariable Long id){
        var response = scheduleService.findScheduleByPatient(id);
        return ResponseEntity.ok().body(response);
    }
}
