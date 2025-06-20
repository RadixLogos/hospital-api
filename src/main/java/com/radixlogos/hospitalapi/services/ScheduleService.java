package com.radixlogos.hospitalapi.services;

import com.radixlogos.hospitalapi.dtos.responses.ScheduleResponseDTO;
import com.radixlogos.hospitalapi.repositories.PatientRepository;
import com.radixlogos.hospitalapi.repositories.ScheduleRepository;
import com.radixlogos.hospitalapi.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AuditLogService auditLogService;

    @Transactional(readOnly = true)
    public ScheduleResponseDTO findScheduleByPatient(Long patientId){
        if(!patientRepository.existsById(patientId)){
            throw new ResourceNotFoundException("Paciente não encontrado");
        }
        var patient = patientRepository.getReferenceById(patientId);
        var entitySchedule = scheduleRepository.findByPatient(patient)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado"));
        auditLogService.logAction("Busca de agendamento de paciente com id " + patientId);
        return ScheduleResponseDTO.fromSchedule(entitySchedule);
    }
}
