package com.radixlogos.hospitalapi.entities;

import com.radixlogos.hospitalapi.enums.Gender;
import com.radixlogos.hospitalapi.enums.NationalityType;
import com.radixlogos.hospitalapi.enums.WorkShiftType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_employee")
public abstract class Employee extends Person {

    private BigDecimal salary = BigDecimal.ZERO;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paySheet_id")
    private PaySheet paySheet;
    private WorkShiftType workShift;
    @ManyToOne
    @JoinColumn(name = "departament_id")
    private Department department;

    protected Employee() {
        super();
    }

    public Employee(BigDecimal salary, PaySheet paySheet, WorkShiftType workShift, Department department, String name, String lastName, Gender gender, String cpf, LocalDate birthDate, String telephoneNumber,
                    String cellphoneNumber, String email, Address address, NationalityType nationality, Hospital hospital) {
        super(name, lastName, gender, cpf, birthDate, telephoneNumber, cellphoneNumber, email, address, nationality, hospital);
        this.salary = salary;
        this.paySheet = paySheet;
        this.department = department;
        this.workShift = workShift;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public PaySheet getPaysheet() {
        return paySheet;
    }

    public void setPaysheet(PaySheet paySheet) {
        this.paySheet = paySheet;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public WorkShiftType getWorkShift() {
        return workShift;
    }

    public void setWorkShift(WorkShiftType workShift) {
        this.workShift = workShift;
    }

}
