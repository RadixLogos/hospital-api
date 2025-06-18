package com.radixlogos.hospitalapi.repositories;



import com.radixlogos.hospitalapi.entities.Hospital;

import java.util.Optional;

public interface HospitalRepository extends GeneralRepository<Hospital, Long> {
    public Optional<Hospital> findHospitalByName(String name);
    public boolean existsByAddress_ZipCodeAndAddress_Number(String zipCode, String number);
}
