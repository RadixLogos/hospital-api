package com.radixlogos.hospitalapi.entities;

import com.radixlogos.hospitalapi.enums.DepartmentType;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_department")
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private DepartmentType departmentType;
    @Column(name = "floor_number", nullable = false)
    private Integer floorNumber;
    @Column(name = "number_of_beds", nullable = false)
    private Integer numberOfBeds;

    @ManyToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private Hospital hospital;
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();
    @OneToMany(mappedBy = "inpatientDepartment")
    private Set<Patient> hospitalizedPatients = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "responsible_doctor_id", referencedColumnName = "id")
    private Doctor responsibleDoctor;

    protected Department() {
    }

    public Department(DepartmentType departmentType, int floorNumber, int numberOfBeds) {
        this.departmentType = departmentType;
        this.floorNumber = floorNumber;
        this.numberOfBeds =numberOfBeds;
    }

    public Long getId() {
        return id;
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;

    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Set<Employee> getEmployee() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

   public Set<Patient> getHospitalizedPatients() {
        return Collections.unmodifiableSet(hospitalizedPatients);
    }

    public void addHospitalizedPatient(Patient hospitalizedPatient) {
        hospitalizedPatients.add(hospitalizedPatient);
    }

    public Doctor getResponsibleDoctor() {
        return responsibleDoctor;
    }

    public void setResponsibleDoctor(Doctor responsibleDoctor) {
        this.responsibleDoctor = responsibleDoctor;
    }


    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
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
        Department other = (Department) obj;
        return Objects.equals(id, other.id);
    }


}
