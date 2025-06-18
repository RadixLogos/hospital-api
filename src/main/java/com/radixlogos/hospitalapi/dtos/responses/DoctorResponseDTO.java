package com.radixlogos.hospitalapi.dtos.responses;



import com.radixlogos.hospitalapi.entities.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public record DoctorResponseDTO(String name, String lastName, String hospitalName) {
    public static DoctorResponseDTO fromDoctor(Doctor doctor) {
        return new DoctorResponseDTO(
                doctor.getName(),
                doctor.getLastName(),
                doctor.getHospital().getName());
    }


}
