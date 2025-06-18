package com.radixlogos.hospitalapi.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_medical_exam")
public class MedicalExam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String physicalExam;
    @ElementCollection
    @CollectionTable(name = "complementary_exams", joinColumns = @JoinColumn(name = "medical_exam_id"))
    private Set<String> complementaryExams = new HashSet<>();
    private LocalDate examDate;
    private String methods; // The methods used to get the result for the exams
    @ElementCollection
    @CollectionTable(name = "exam_measures_results", joinColumns = @JoinColumn(name = "medical_exam_id"))
    private Set<String> examMeasuresResult = new HashSet<>(); // Details of any measure taken in the exams (example: pressure, blood glucose level)
    private String doctorObservations; // Comments from the doctor about the exams
    @ElementCollection
    @CollectionTable(name = "exams_history", joinColumns = @JoinColumn(name = "medical_exam_id"))
    private Set<String> examsHistory = new HashSet<>(); // This will work as a log of the events in the medical exam

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "medical_exam_id"),
            inverseJoinColumns = @JoinColumn(name ="doctor_id"))
    private Set<Doctor> doctors = new HashSet<>();
    @OneToOne(mappedBy = "medicalExam")
    private MedicalRecord medicalRecord;
    protected MedicalExam() {
    }

    public MedicalExam(String physicalExam, String doctorObservations, LocalDate examDate) {
        this.physicalExam = physicalExam;
        this.doctorObservations = (doctorObservations);
        this.examDate = examDate;
    }

    public Long getId() {
        return id;
    }

    public String getPhysicalExam() {
        return physicalExam;
    }

    public void setPhysicalExam(String physicalExam) {
        this.physicalExam =physicalExam;
    }

    public Set<String> getComplementaryExams() {
        return complementaryExams;
    }

    public void addComplementaryExams(String complementaryExam) {
        this.complementaryExams.add(complementaryExam);
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public Set<String> getExamMeasuresResult() {
        return examMeasuresResult;
    }

    public void addExamMeasureResult(String examMeasuresResult) {
        this.examMeasuresResult.add(examMeasuresResult);
    }

    public String getDoctorObservations() {
        return doctorObservations;
    }

    public void setDoctorObservations(String doctorObservations) {
        this.doctorObservations = doctorObservations;
    }


    public Set<String> getExamsHistory() {
        return examsHistory;
    }

    public void addExamHistory(String examsHistory) {
        this.examsHistory.add(examsHistory);
    }

    public void setDoctors(Doctor doctor) {
        this.doctors.add(doctor);
    }

    public Set<Doctor> getDoctors() {
        return doctors;
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
        MedicalExam other = (MedicalExam) obj;
        return Objects.equals(id, other.id);
    }


}
