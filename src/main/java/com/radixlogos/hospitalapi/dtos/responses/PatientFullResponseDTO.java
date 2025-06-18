package com.radixlogos.hospitalapi.dtos.responses;

import com.radixlogos.hospitalapi.dtos.insertions.PersonInsertDTO;
import com.radixlogos.hospitalapi.entities.Patient;

public record PatientFullResponseDTO(
        Long id,
        Long hospitalId,
        String mothersName,
        String profession,
        PersonInsertDTO person

) {
    public static PatientFullResponseDTO fromPatient(Patient p){
        var person = new PersonInsertDTO(
                p.getName(),
                p.getLastName(),
                p.getGender(),
                p.getCpf(),
                p.getBirthDate(),
                p.getTelephoneNumber(),
                p.getCellphoneNumber(),
                p.getEmail(),
                p.getAddress(),
                p.getNationality());
        return new PatientFullResponseDTO(
                p.getId(),
                p.getHospital().getId(),
                p.getMothersName(),
                p.getProfession(),
                person
        );
    }
}
