package com.radixlogos.hospitalapi.repositories;

import com.radixlogos.hospitalapi.entities.Patient;
import com.radixlogos.hospitalapi.entities.Schedule;

import java.util.Optional;

public interface ScheduleRepository extends GeneralRepository<Schedule,Long>{

    Optional<Schedule> findByPatient(Patient patient);
}
