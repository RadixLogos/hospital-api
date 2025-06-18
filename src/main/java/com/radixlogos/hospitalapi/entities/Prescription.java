package com.radixlogos.hospitalapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_prescription")
public class Prescription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "prescription_date", nullable = false)
    private LocalDate prescriptionDate;
    @Column(name = "medication_detail", nullable = false)
    private String medicationDetail;
    @Column(name = "pharmacist_instruction", nullable = false)
    private String pharmacistInstruction;
    @Column(name = "usage_instruction", nullable = false)
    private String usageInstruction;
    @Column(name = "additional_information")
    private String additionalInformation; //  The doctor can add some comments or additional information to the prescription

    @OneToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    public Prescription() {
    }

    public Prescription(LocalDate prescriptionDate, Doctor doctor, String medicationDetail,
                        String pharmacistInstruction, String usageInstruction, String additionalInformation) {
        this.prescriptionDate = prescriptionDate;
        this.doctor = doctor;
        this.medicationDetail = medicationDetail;
        this.pharmacistInstruction = pharmacistInstruction;
        this.usageInstruction = usageInstruction;
        this.additionalInformation = additionalInformation;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getMedicationDetail() {
        return medicationDetail;
    }

    public void setMedicationDetail(String medicationDetail) {
        this.medicationDetail = medicationDetail;
    }

    public String getPharmacistInstruction() {
        return pharmacistInstruction;
    }

    public void setPharmacistInstruction(String pharmacistInstruction) {
        this.pharmacistInstruction =pharmacistInstruction;
    }

    public String getUsageInstruction() {
        return usageInstruction;
    }

    public void setUsageInstruction(String usageInstruction) {
        this.usageInstruction = usageInstruction;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
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
        Prescription other = (Prescription) obj;
        return Objects.equals(id, other.id);
    }


}
