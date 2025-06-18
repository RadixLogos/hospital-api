package com.radixlogos.hospitalapi.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_hospital")
public class Hospital implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Embedded
    private Address address;
    @Column
    private String telephone;
    private BigDecimal consultationPrice = BigDecimal.ZERO;
    private BigDecimal hospitalizationPrice = BigDecimal.ZERO;
    private BigDecimal hospitalCash = BigDecimal.ZERO;

    @OneToMany(mappedBy = "hospital")
    private Set<Person> persons = new HashSet<>();
    @OneToMany(mappedBy = "hospital")
    private Set<Department> departments = new HashSet<>();


    public Hospital() {
    }

    public Hospital(String name, Address address, String telephone) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public Set<Department> getDepartments() {
        return Collections.unmodifiableSet(departments);
    }

     public void addDepartment(Department department) {
        Objects.requireNonNull(department, "O departamento não pode ser nulo!");
        this.departments.add(department);
    }

    public BigDecimal getConsultationPrice() {
        return consultationPrice;
    }

    public void setConsultationPrice(BigDecimal consultationPrice) {
        this.consultationPrice = consultationPrice;
    }

    public BigDecimal getHospitalizationPrice() {
        return hospitalizationPrice;
    }

    public void setHospitalizationPrice(BigDecimal hospitalizationPrice) {
        this.hospitalizationPrice = hospitalizationPrice;
    }

    public BigDecimal getHospitalCash() {
        return hospitalCash;
    }


    public void setHospitalCash(BigDecimal hospitalCash) {
        this.hospitalCash =hospitalCash;
    }


    public void addHospitalCash(BigDecimal hospitalCash) {
        this.hospitalCash.add(hospitalCash);
    }

    public Set<Person> getPersons() {
        return persons;
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
        Hospital other = (Hospital) obj;
        return Objects.equals(id, other.id);
    }


}
