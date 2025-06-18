package com.radixlogos.hospitalapi.entities;

import com.radixlogos.hospitalapi.enums.Gender;
import com.radixlogos.hospitalapi.enums.NationalityType;
import com.radixlogos.hospitalapi.enums.Specialty;
import com.radixlogos.hospitalapi.enums.WorkShiftType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_doctor")
public class Doctor extends Employee {
    private static final long serialVersionUID = 1L;

    private String professionalRegistration;//CRM, NPI, GMC, etc...
    private Specialty specialty;

    @ManyToMany
    @JoinTable(name = "doctors_and_patients",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Set<Patient> patients = new HashSet<>();
    @OneToMany(mappedBy = "doctor")
    private Set<Schedule> schedules = new HashSet<>();
    @ManyToMany(mappedBy = "doctors")
    Set<MedicalExam> medicalExams = new HashSet<>();

    protected Doctor() {
        super();
    }

    public Doctor(
            String professionalRegistration,
            Specialty specialty,
            BigDecimal salary,
            PaySheet paySheet,
            WorkShiftType workShift,
            Department department,
            String name,
            String lastName,
            Gender gender,
            String cpf,
            LocalDate birthDate,
            String telephoneNumber,
            String cellphoneNumber,
            String email,
            Address address,
            NationalityType nationality,
            Hospital hospital) {
        super(salary, paySheet, workShift, department, name, lastName, gender, cpf, birthDate, telephoneNumber, cellphoneNumber, email, address, nationality, hospital);
        this.professionalRegistration =professionalRegistration;
        this.specialty = specialty;
    }


    public Set<Patient> getPatients() {
        return Collections.unmodifiableSet(patients);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public Set<Schedule> getSchedules() {
        return Collections.unmodifiableSet(schedules);
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getProfessionalRegistration() {
        return professionalRegistration;
    }

    public void setProfessionalRegistration(String professionalRegistration) {
        this.professionalRegistration = professionalRegistration;
    }

    public Set<MedicalExam> getMedicalExams() {
        return medicalExams;
    }

    public void setMedicalExams(MedicalExam medicalExam) {
        this.medicalExams.add(medicalExam);
    }
}
