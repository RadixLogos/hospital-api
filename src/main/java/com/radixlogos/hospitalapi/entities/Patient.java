package com.radixlogos.hospitalapi.entities;

import com.radixlogos.hospitalapi.enums.Gender;
import com.radixlogos.hospitalapi.enums.NationalityType;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_patient")
public class Patient extends Person {
    private static final long serialVersionUID = 1L;
    private String profession;
    private String mothersName;

    @OneToOne
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;
    @ManyToMany(mappedBy = "patients")
    private Set<Doctor> doctors = new HashSet<>();
    @OneToMany(mappedBy = "patient")
    private Set<MedicalRecord> medicalRecordsHistory = new HashSet<>();// Here is the repository to keep all medical records of a patient
    @OneToMany(mappedBy = "patient")
    private Set<Schedule> schedules = new HashSet<>(); // The patient maybe have an appointment with more than one doctor
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department inpatientDepartment;

    public Patient() {
        super();
    }

    // Constructor to create a patient
    public Patient(String name, String lastName, Gender gender, String cpf, LocalDate birthDate, String telephoneNumber,
                   String cellphoneNumber, String email, Address address, NationalityType nationality, Hospital hospital, String profession, String mothersName) {
        super(name, lastName, gender, cpf, birthDate, telephoneNumber, cellphoneNumber, email, address, nationality, hospital);
        this.profession = Objects.requireNonNull(profession, "A profissão não pode ser nula!");
        this.mothersName = Objects.requireNonNull(mothersName, "O nome da mãe não pode ser nulo!");
    }


    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = Objects.requireNonNull(profession, "A profissão não pode ser nula!");
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = Objects.requireNonNull(mothersName, "O nome da mãe não pode ser nulo!");
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Set<Doctor> getDoctors() {
        return Collections.unmodifiableSet(doctors);
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(Objects.requireNonNull(doctor, "O médico não pode ser nulo!"));
    }

    public Set<MedicalRecord> getMedicalRecordsHistory() {
        return Collections.unmodifiableSet(medicalRecordsHistory);
    }

    public Set<Schedule> getSchedules() {
        return Collections.unmodifiableSet(schedules);
    }

    public void addSchedule(Schedule schedule) {
        this.schedules.add(Objects.requireNonNull(schedule, "A agenda não pode ser nula"));
    }

    public Department getDepartment() {
        return inpatientDepartment;
    }

    public void setDepartment(Department inpatientDepartment) {
        this.inpatientDepartment = Objects.requireNonNull(inpatientDepartment, "O departamento não pode ser nulo!");
    }


}
