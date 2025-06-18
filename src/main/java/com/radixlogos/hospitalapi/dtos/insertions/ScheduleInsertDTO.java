package com.radixlogos.hospitalapi.dtos.insertions;

import com.radixlogos.hospitalapi.entities.Address;
import com.radixlogos.hospitalapi.enums.HospitalServiceType;
import com.radixlogos.hospitalapi.enums.ScheduleType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

public record ScheduleInsertDTO(
        @FutureOrPresent(message = "A data do agendamento não pode ser passada")
        LocalDate scheduleDate,
        ScheduleType scheduleType,
        HospitalServiceType hospitalServiceType,
        @Valid
        Address address,
        String teleconsultationLink) {
}
