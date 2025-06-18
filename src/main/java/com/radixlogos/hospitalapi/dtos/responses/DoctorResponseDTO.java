package com.radixlogos.hospitalapi.dtos.responses;



import com.radixlogos.hospitalapi.entities.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public record DoctorResponseDTO(String name, String lastName, String professionalRegistration) {
    public static DoctorResponseDTO doctorsEssentialInformation(Doctor doctor) {
        return new DoctorResponseDTO(doctor.getName(), doctor.getLastName(), doctor.getProfessionalRegistration());
    }

    public static DoctorResponseDTO doctorsName(Doctor doctor) {
        return new DoctorResponseDTO(doctor.getName(), doctor.getLastName(), null);
    }

    public static List<DoctorResponseDTO> doctorsEssentialInformationList(Set<Doctor> doctors) {
        List<DoctorResponseDTO> doctorsDTO = new ArrayList<>();
        for (Doctor doctor : doctors) {
            doctorsDTO.add(DoctorResponseDTO.doctorsEssentialInformation(doctor));
        }
        return doctorsDTO;
    }
}
