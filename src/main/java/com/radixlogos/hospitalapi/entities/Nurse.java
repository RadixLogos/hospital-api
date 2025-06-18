package com.radixlogos.hospitalapi.entities;

import com.radixlogos.hospitalapi.enums.Gender;
import com.radixlogos.hospitalapi.enums.NationalityType;
import com.radixlogos.hospitalapi.enums.WorkShiftType;
import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_nurse")
public class Nurse extends Employee {
    private static final long serialVersionUID = 1L;
    @Column(unique = true)
    private String corenRegister;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor supervisingDoctor;

    protected Nurse() {
        super();
    }

    public Nurse(String corenRegister, Doctor supervisingDoctor, BigDecimal salary, PaySheet paySheet, WorkShiftType workShift, Department department, String name, String lastName, Gender gender, String cpf,
                 LocalDate birthDate, String telephoneNumber, String cellphoneNumber, String email, Address address, NationalityType nationality, Hospital hospital) {
        super(salary, paySheet, workShift, department, name, lastName, gender, cpf, birthDate, telephoneNumber, cellphoneNumber, email, address, nationality, hospital);
        this.corenRegister = corenRegister;
        this.supervisingDoctor = supervisingDoctor;
    }

    public String getCorenRegister() {
        return corenRegister;
    }

    public void setCorenRegister(String corenRegister) {
        this.corenRegister = corenRegister;
    }

    public Doctor getSupervisingDoctor() {
        return supervisingDoctor;
    }

    public void setSupervisingDoctor(Doctor supervisingDoctor) {
        this.supervisingDoctor = supervisingDoctor;
    }

}
