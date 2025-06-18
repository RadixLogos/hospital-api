package com.radixlogos.hospitalapi.repositories;


import com.radixlogos.hospitalapi.entities.Doctor;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends GeneralRepository<Doctor, Long> {

}
