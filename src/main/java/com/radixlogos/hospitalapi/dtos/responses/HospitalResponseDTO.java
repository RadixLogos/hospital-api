package com.radixlogos.hospitalapi.dtos.responses;


import com.radixlogos.hospitalapi.entities.Address;
import com.radixlogos.hospitalapi.entities.Hospital;

public record HospitalResponseDTO(Long id, String name, Address address, String telephone) {
    public static HospitalResponseDTO fromHospital(Hospital hospital) {
        return new HospitalResponseDTO(hospital.getId(), hospital.getName(), hospital.getAddress(), hospital.getTelephone());
    }
}
