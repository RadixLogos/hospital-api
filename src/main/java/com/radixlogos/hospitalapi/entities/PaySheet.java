package com.radixlogos.hospitalapi.entities;

import com.radixlogos.hospitalapi.enums.PaySheetStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_paysheet")
public class PaySheet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "paySheet")
    private Employee employee;
    @Column(nullable = false)
    private BigDecimal payValue;
    @Column
    private LocalDate payDate;
    private PaySheetStatus status;

    protected PaySheet() {
    }

    public PaySheet(Employee employee, BigDecimal payValue, LocalDate payDate, PaySheetStatus paySheetStatus) {
        this.employee = Objects.requireNonNull(employee, "O empregado não pode ser nulo!");
        this.payValue = (payValue.compareTo(BigDecimal.ZERO) <= 0) ? BigDecimal.ONE : payValue;
        this.payDate = Objects.requireNonNull(payDate, "A data de pagamento não pode ser nula!");
        this.status = (paySheetStatus != null) ? paySheetStatus : PaySheetStatus.PENDDING;
    }

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = Objects.requireNonNull(employee, "O empregado não pode ser nulo!");
    }

    public BigDecimal getPayValue() {
        return payValue;
    }

    public void setPayValue(BigDecimal payValue) {
        this.payValue = (payValue.compareTo(BigDecimal.ZERO) <= 0) ? BigDecimal.ONE : payValue;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = Objects.requireNonNull(payDate, "A data de pagamento não pode ser nula!");
    }

    public PaySheetStatus getStatus() {
        return status;
    }

    public void setStatus(PaySheetStatus status) {
        this.status = Objects.requireNonNull(status, "O status do pagamento não pode ser nulo!");
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
        PaySheet other = (PaySheet) obj;
        return Objects.equals(id, other.id);
    }


}
