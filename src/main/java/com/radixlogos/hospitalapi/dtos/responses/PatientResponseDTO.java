package com.radixlogos.hospitalapi.dtos.responses;


import com.radixlogos.hospitalapi.entities.Patient;
import com.radixlogos.hospitalapi.enums.Gender;

public record PatientResponseDTO(Long id, String name, String lastName, Gender gender, String profession, String mothersName,
                                 String email, Long hospitalId) {
    public static PatientResponseDTO fromPatient(Patient patient) {
        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getLastName(),
                patient.getGender(),
                patient.getProfession(),
                patient.getMothersName(),
                patient.getEmail(),
                patient.getHospital().getId());
    }

}
