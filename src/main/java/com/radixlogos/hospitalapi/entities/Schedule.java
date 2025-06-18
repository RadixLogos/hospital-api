package com.radixlogos.hospitalapi.entities;

import com.radixlogos.hospitalapi.enums.HospitalServiceType;
import com.radixlogos.hospitalapi.enums.ScheduleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_schedule")
public class Schedule implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private ScheduleType scheduleType;
    @Column(name = "hospital_service_type", nullable = false)
    private HospitalServiceType hospitalServiceType;
    @Embedded
    private Address address;
    @Column(name = "teleconsultation_link")
    private String teleconsultationLink;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // The address or the link for tele-consultation are added after the schedule been created
    public Schedule(LocalDate date, ScheduleType scheduleType, HospitalServiceType hospitalServiceType, Patient patient, Doctor doctor) {
        this.date = date;
        this.scheduleType = scheduleType;
        this.hospitalServiceType = hospitalServiceType;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

    public HospitalServiceType getHospitalServiceType() {
        return hospitalServiceType;
    }

    public void setHospitalServiceType(HospitalServiceType hospitalServiceType) {
        this.hospitalServiceType = hospitalServiceType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getTeleconsultationLink() {
        return teleconsultationLink;
    }

    public void setTeleconsultationLink(String teleconsultationLink) {
        this.teleconsultationLink = teleconsultationLink;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Schedule other = (Schedule) obj;
        return Objects.equals(id, other.id);
    }


}
