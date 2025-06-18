package com.radixlogos.hospitalapi.repositories;


import com.radixlogos.hospitalapi.entities.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends GeneralRepository<Patient, Long> {

}
