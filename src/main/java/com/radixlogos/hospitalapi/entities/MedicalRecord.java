package com.radixlogos.hospitalapi.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_medical_record")
public class MedicalRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "hypothetical_diagnostic", nullable = false)
    private String hypotheticalDiagnostic;
    @Column(name = "final_diagnostic")
    private String finalDiagnostic;
    @ElementCollection
    @CollectionTable(name = "symptoms", joinColumns = @JoinColumn(name = "medical_record_id"))
    private Set<String> symptoms = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @OneToOne
    @JoinColumn(name = "medical_exam_id")
    private MedicalExam medicalExam; // Contains all the exams that are necessary to the medical record
    @ManyToOne
    @JoinColumn(name = "medical_history_id")
    private MedicalHistory medicalHistory; // The medical history of the patient who's the medical record belongs
    @OneToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    public MedicalRecord() {
    }

    public MedicalRecord(Patient patient, MedicalHistory medicalHistory, String hypotheticalDiagnostic) {
        this.patient = patient;
        this.medicalHistory = medicalHistory;
        this.hypotheticalDiagnostic = hypotheticalDiagnostic;
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = Objects.requireNonNull(patient, "O paciente não pode ser nulo!");
    }

    public Set<String> getSymptoms() {
        return Collections.unmodifiableSet(symptoms);
    }

    public void setSymptoms(Set<String> symptoms) {
        this.symptoms = (symptoms != null) ? symptoms : new HashSet<>();
    }

    public MedicalExam getMedicalExam() {
        return medicalExam;
    }

    public void setMedicalExam(MedicalExam medicalExam) {
        this.medicalExam = Objects.requireNonNull(medicalExam, "O exame médico não pode ser nulo!");
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = Objects.requireNonNull(medicalHistory, "O histórico médico não pode ser nulo!");
    }

    public String getHypotheticalDiagnostic() {
        return hypotheticalDiagnostic;
    }

    public void setHypotheticalDiagnostic(String hypotheticalDiagnostic) {
        this.hypotheticalDiagnostic = Objects.requireNonNull(hypotheticalDiagnostic, "O diagnóstico hipotético não pode ser nulo!");
    }

    public String getFinalDiagnostic() {
        return finalDiagnostic;
    }

    public void setFinalDiagnostic(String finalDiagnostic) {
        this.finalDiagnostic = Objects.requireNonNull(finalDiagnostic, "O diagnóstico final não pode ser nulo!");
    }


    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = Objects.requireNonNull(prescription, "A receita médica não pode ser nula!");
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
        MedicalRecord other = (MedicalRecord) obj;
        return Objects.equals(id, other.id);
    }


}
