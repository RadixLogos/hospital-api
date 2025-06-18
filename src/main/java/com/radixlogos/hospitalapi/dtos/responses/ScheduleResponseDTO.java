package com.radixlogos.hospitalapi.dtos.responses;

import com.radixlogos.hospitalapi.entities.Address;
import com.radixlogos.hospitalapi.entities.Schedule;
import com.radixlogos.hospitalapi.enums.HospitalServiceType;
import com.radixlogos.hospitalapi.enums.ScheduleType;
import java.time.LocalDate;

public record ScheduleResponseDTO(
        Long id,
        LocalDate scheduleDate,
        ScheduleType scheduleType,
        HospitalServiceType hospitalServiceType,
        Address address,
        String teleconsultationLink,
        DoctorResponseDTO doctor,
        PatientResponseDTO patient
) {
    public static ScheduleResponseDTO fromSchedule(Schedule schedule){
        var doctorDTO = DoctorResponseDTO.fromDoctor(schedule.getDoctor());
        var patientDTO = PatientResponseDTO.fromPatient(schedule.getPatient());
        return new ScheduleResponseDTO(
                schedule.getId(),
                schedule.getScheduleDate(),
                schedule.getScheduleType(),
                schedule.getHospitalServiceType(),
                schedule.getAddress(),
                schedule.getTeleconsultationLink(),
                doctorDTO,
                patientDTO
        );
    }
}
