package com.radixlogos.hospitalapi.entities;

import com.radixlogos.hospitalapi.enums.Gender;
import com.radixlogos.hospitalapi.enums.NationalityType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "tb_person")
public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private NationalityType nationality;
    private Gender gender;
    private String cpf;
    private LocalDate birthDate;
    private String telephoneNumber;
    private String cellphoneNumber;
    private String email;
    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    protected Person() {
    }

    public Person(
            String name, String lastName, Gender gender, String cpf, LocalDate birthDate, String telephoneNumber,
            String cellphoneNumber, String email, Address address, NationalityType nationality, Hospital hospital) {
        this.name = Objects.requireNonNull(name, "O nome não pode ser nulo!");
        this.lastName = Objects.requireNonNull(lastName, "O sobrenome não pode ser nulo!");
        this.gender = Objects.requireNonNull(gender, "O sexo não pode ser nulo!");
        this.cpf = Objects.requireNonNull(cpf, "O CPF não pode ser nulo");
        this.birthDate = Objects.requireNonNull(birthDate, "A data de nascimento não pode ser nula!");
        this.telephoneNumber = telephoneNumber;
        this.cellphoneNumber = Objects.requireNonNull(cellphoneNumber, "O celular não pode ser nulo!");
        this.email = Objects.requireNonNull(email, "O email não pode ser nulo!");
        this.address = Objects.requireNonNull(address, "O endereço não pode ser nulo!");
        this.nationality = Objects.requireNonNull(nationality);
        this.hospital = Objects.requireNonNull(hospital, "O hospital não pode ser nulo!");

    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "O nome não pode ser nulo!");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = Objects.requireNonNull(lastName, "O sobrenome não pode ser nulo!");
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = Objects.requireNonNull(gender, "O sexo não pode ser nulo!");
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = Objects.requireNonNull(cpf, "O CPF não pode ser nulo");
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = Objects.requireNonNull(birthDate, "A data de nascimento não pode ser nula!");
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = Objects.requireNonNull(cellphoneNumber, "O celular não pode ser nulo!");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = Objects.requireNonNull(email, "O email não pode ser nulo!");
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = Objects.requireNonNull(address, "O endereço não pode ser nulo!");
    }

    public NationalityType getNationality() {
        return nationality;
    }

    public void setNationality(NationalityType nationality) {
        this.nationality = Objects.requireNonNull(nationality, "A nacionalidade não pode ser nula!");
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = Objects.requireNonNull(hospital, "O hospital não pode ser nulo!");
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
        Person other = (Person) obj;
        return Objects.equals(id, other.id);
    }


}
