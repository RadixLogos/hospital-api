package com.radixlogos.hospitalapi.entities;

import com.radixlogos.hospitalapi.enums.BloodType;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_medical_history_record")
public class MedicalHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double weight;
    private double height;
    private BloodType bloodType;
    private boolean smoker;
    private boolean alcoholConsumer;
    private boolean physicalExerciser;
    private boolean medicationUser;
    @ElementCollection
    @CollectionTable(name = "patients_medicines_information", joinColumns = @JoinColumn(name = "medical_history_id"))
    private Set<String> medicinesUsedInformation = new HashSet<>();
    private String familyHealthHistory;
    private String patientHealthHistory;
    @ElementCollection
    @CollectionTable(name = "surgeries_history", joinColumns = @JoinColumn(name = "medical_history_id"))
    private Set<String> surgeriesHistory = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "patients_chronic_diseases_information", joinColumns = @JoinColumn(name = "medical_history_id"))
    private Set<String> chronicDiseasesInformation = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "patients_symptoms", joinColumns = @JoinColumn(name = "medical_history_id"))
    private Set<String> patientSymptoms = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "patients_diagnostics", joinColumns = @JoinColumn(name = "medical_history_id"))
    private Set<String> diagnostics = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "patients_hospitalizations", joinColumns = @JoinColumn(name = "medical_history_id"))
    private Set<String> hospitalizations = new HashSet<>(); // Describe the circumstances, for how long and other details 

    @OneToMany(mappedBy = "medicalHistory")
    private Set<MedicalRecord> medicalRecords = new HashSet<>();
    protected MedicalHistory() {
    }


    public MedicalHistory(double weight, double height, BloodType bloodType, boolean smoker, boolean alcoholConsumer,
                          boolean physicalExerciser, boolean medicationUser,String familyHealthHistory,
                          String patientHealthHistory) {
        this.weight = weight;
        this.height = height;
        this.bloodType = bloodType;
        this.smoker = smoker;
        this.alcoholConsumer = alcoholConsumer;
        this.physicalExerciser = physicalExerciser;
        this.medicationUser = medicationUser;
        this.familyHealthHistory = familyHealthHistory;
        this.patientHealthHistory = patientHealthHistory;

    }

    public Long getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public boolean isAlcoholConsumer() {
        return alcoholConsumer;
    }

    public void setAlcoholConsumer(boolean alcoholConsumer) {
        this.alcoholConsumer = alcoholConsumer;
    }

    public boolean isPhysicalExerciser() {
        return physicalExerciser;
    }

    public void setPhysicalExerciser(boolean physicalExerciser) {
        this.physicalExerciser = physicalExerciser;
    }

    public boolean isMedicationUser() {
        return medicationUser;
    }

    public void setMedicationUser(boolean medicationUser) {
        this.medicationUser = medicationUser;
    }

    public Set<String> getMedicinesUsedInformation() {
        return Collections.unmodifiableSet(medicinesUsedInformation);
    }


    public String getFamilyHealthHistory() {
        return familyHealthHistory;
    }

    public void setFamilyHealthHistory(String familyHealthHistory) {
        this.familyHealthHistory = familyHealthHistory;
    }

    public String getPatientHealthHistory() {
        return patientHealthHistory;
    }

    public void setPatientHealthHistory(String patientHealthHistory) {
        this.patientHealthHistory = patientHealthHistory;
    }

    public Set<String> getSurgeriesHistory() {
        return Collections.unmodifiableSet(surgeriesHistory);
    }

    public Set<String> getChronicDiseasesInformation() {
        return Collections.unmodifiableSet(chronicDiseasesInformation);
    }


    public Set<String> getPatientSymptoms() {
        return Collections.unmodifiableSet(patientSymptoms);
    }


    public Set<String> getDiagnostics() {
        return Collections.unmodifiableSet(diagnostics);
    }


    public Set<String> getHospitalizations() {
        return Collections.unmodifiableSet(hospitalizations);
    }

    public Set<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
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
        MedicalHistory other = (MedicalHistory) obj;
        return Objects.equals(id, other.id);
    }


}
