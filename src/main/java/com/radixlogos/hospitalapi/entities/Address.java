package com.radixlogos.hospitalapi.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 32, message = "O nome da cidade deve ter no máximo 32 caracteres")
    @NotBlank(message = "O nome da cidade não pode estar em branco!")
    String city;
    @Size(max = 32, message = "O nome do estado deve ter no máximo 32 caracteres")
    @NotBlank(message = "O nome do estado não pode estar em branco!")
    String state;
    @Size(max = 32, message = "O nome da rua deve ter no máximo 32 caracteres")
    @NotBlank(message = "O nome da rua não pode estar em branco!")
    String streetName;
    @Size(max = 32, message = "O nome do bairro deve ter no máximo 32 caracteres")
    @NotBlank(message = "O nome do bairro não pode estar em branco!")
    String neighborhood;
    @Pattern(regexp = "^[0-9A-Za-z/-]+$", message = "Número de residência inválido!")
    @NotBlank(message = "O número não pode estar em branco")
    String number;
    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "CEP inválido! Por favor digite o CEP no formato 12345-678 ou 12345678")
    @NotBlank(message = "O CEP não pode estar em branco")
    String zipCode;
    @Size(max = 300, message = "Complemento deve ter no máximo 300 caracteres!")
    String complement;
    protected Address() {
    }

    public Address(
            String city,
            String state,
            String streetName,
            String neighborhood,
            String number,
            String zipCode,
            String complement) {
        this.city = city;
        this.state = state;
        this.streetName = streetName;
        this.neighborhood = neighborhood;
        this.number = number;
        this.zipCode = zipCode;
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }


    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        sB.append(
                "Cidade: " + city +
                        "\nEstado: " + state +
                        "\nRua: " + streetName +
                        "\nBairro: " + neighborhood +
                        "\nNumero: " + number +
                        "\nCep: " + zipCode);
        return sB.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, complement, neighborhood, number, state, streetName, zipCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        return Objects.equals(city, other.city) && Objects.equals(complement, other.complement)
                && Objects.equals(neighborhood, other.neighborhood) && Objects.equals(number, other.number)
                && Objects.equals(state, other.state) && Objects.equals(streetName, other.streetName)
                && Objects.equals(zipCode, other.zipCode);
    }


}
